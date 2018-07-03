public class CommandBuilderChangeOmuName extends CommandBuilderForOmu {

    public void pushScript(Omu omu) {
        SSHDemo sshDemo = new SSHDemo();

        if (omu.getOmuExternalIp() != null && sshDemo.openConnection(omu.getOmuExternalIp(), 22, "lgnusr", "mtsosUSER@123", 120000)) {
            waitTime(1000L);
            sshDemo.sendCommand("su");
            waitTime(1000L);
            sshDemo.sendCommand("mtsosSU@123");
            waitTime(1000L);
            sshDemo.sendCommand("/etc/rc.d/omud stop");
            waitTime(20000L);
            sshDemo.sendCommand("cd /mbsc/bam/version_a/bin/bam");
            waitTime(1000L);
            sshDemo.sendCommand("./omutool computername " + omu.getOmuName());
            waitTime(1000L);
            sshDemo.sendCommand("/etc/rc.d/omud start");
            waitTime(20000L);
            System.out.println("result: \n" + sshDemo.recvData().toString());
            waitTime(1000L);
            sshDemo.close();
        } else {
            System.out.println("connection error" + omu.toString());
        }
    }


}
