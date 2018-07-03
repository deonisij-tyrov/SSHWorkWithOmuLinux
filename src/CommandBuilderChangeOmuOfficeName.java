
public class CommandBuilderChangeOmuOfficeName extends CommandBuilderForOmu {

    private String getCurrentName(String s) {
        System.out.println(s);
        String[] strings = s.split("\r\n");
        for (String l : strings) {
            if (l.contains("officename=")) {
                return l;
            }
        }
        return null;
    }

    @Override
    public void pushScript(Omu omu) {
        SSHDemo sshDemo = new SSHDemo();

        if (omu.getOmuExternalIp() != null && sshDemo.openConnection(omu.getOmuExternalIp(), 22, "lgnusr", "mtsosUSER@123", 120000)) {
            waitTime(1000L);
            sshDemo.sendCommand("su");
            waitTime(1000L);
            sshDemo.sendCommand("mtsosSU@123");
            waitTime(1000L);
            sshDemo.sendCommand("cd /mbsc/bam/common/");
            waitTime(1000L);
            sshDemo.sendCommand("cat reg.ini");
            waitTime(1000L);
            String s = getCurrentName(sshDemo.recvData());
            if (s == null) {
                System.out.println("ошибка выполнения!!!");
                return;
            }
            sshDemo.sendCommand("sed -i.bak 's/" + s + "/officename=" + omu.getOmuName() + "/' reg.ini");
            waitTime(1000L);
            System.out.println("result: \n" + sshDemo.recvData());
            waitTime(1000L);
            sshDemo.close();
        } else {
            System.out.println("connection error" + omu.toString());
        }
    }
}
