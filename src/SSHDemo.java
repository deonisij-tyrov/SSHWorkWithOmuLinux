import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SSHDemo {
    private JSch jSch;
    private Session session;
    private Channel channel;
    private InputStream inputStream;
    private OutputStream outputStream;

    public boolean openConnection(String host, int port, String userName, String password, int timeout) {
        boolean blResult = false;

        this.jSch = new JSch();
        //set sftp server no check key when login
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        this.jSch.setConfig(config);

        try {
            this.session = this.jSch.getSession(userName, host, port);
            this.session.setPassword(password);
            this.session.connect(timeout);
            this.channel = this.session.openChannel("shell");
            this.channel.connect();
            this.inputStream = this.channel.getInputStream();
            this.outputStream = this.channel.getOutputStream();
            blResult = true;
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blResult;
    }

    public boolean sendCommand(String command) {
//        System.out.println(command);
        boolean blResult = false;
        command += "\r";
        if (this.channel != null) {
            try {
                this.outputStream.write(command.getBytes());
                this.outputStream.flush();
                blResult = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return blResult;
    }

    public String recvData() {
        String data = null;

        try {
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(this.inputStream);
            if (this.channel != null && this.inputStream.available() > 0) {
                int isAvailable = this.inputStream.available();
                while (isAvailable > 0) {
                    byte[] buffer = new byte[isAvailable];
                    int isByteRead = this.inputStream.read(buffer);
                    isAvailable = isAvailable - isByteRead;
                    data += new String(buffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void close() {
        if (this.session != null) {
            this.session.disconnect();
        }
        if (this.channel != null) {
            this.channel.disconnect();
        }

        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (this.outputStream != null) {
            try {
                this.outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.jSch = null;
    }
}
