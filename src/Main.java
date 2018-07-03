public class Main {
    public static void main(String[] args) {
        chabgeOfficeName();
    }

    private static void chabgeOfficeName() {
        CommandBuilderChangeOmuOfficeName cb = new CommandBuilderChangeOmuOfficeName();
        cb.getOmuInfo("BSC699_Minsk6\n" +
                "BSC699_OMU_1\n" +
                "0\n" +
                "12\n" +
                "172.31.3.227\n" +
                "BSC699_OMU_0\n" +
                "0\n" +
                "10\n" +
                "172.31.3.226");
        Omu omu2 = cb.getOmu2();
        if (omu2 != null) {
            cb.pushScript(omu2);
        }
    }

    private static void changeOmuName() {
        CommandBuilderChangeOmuName commandBuilderOmu = new CommandBuilderChangeOmuName();
        commandBuilderOmu.getOmuInfo("BSC600_Mogilev2\n" +
                "Linux\n" +
                "0\n" +
                "23\n" +
                "172.31.30.23");

        Omu omu2 = commandBuilderOmu.getOmu2();
        if (omu2.getOmuName() != null) {
            commandBuilderOmu.pushScript(omu2);
        }

        Omu omu1 = commandBuilderOmu.getOmu1();
        if (omu1.getOmuName() != null) {
            commandBuilderOmu.pushScript(omu1);
        }
    }
}
