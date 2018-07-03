public abstract class CommandBuilderForOmu {
    private Omu omu1 = new Omu();
    private Omu omu2 = new Omu();

    public Omu getOmu1() {
        return omu1;
    }

    public Omu getOmu2() {
        return omu2;
    }

    public abstract void pushScript(Omu omu);

    protected void waitTime(Long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getOmuInfo(String info) {
        String[] massInfo = info.split("\n");
        System.out.println(massInfo[0]);
        String BscName = massInfo[0];

        try {
            omu1.setOmuSlot(Integer.parseInt(massInfo[3]));
            omu2.setOmuSlot(Integer.parseInt(massInfo[7]));
            omu1.setOmuExternalIp(massInfo[4]);
            omu2.setOmuExternalIp(massInfo[8]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("нет такого эл-та в массиве - " + e);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (omu1.getOmuSlot() < omu2.getOmuSlot()) {
            omu1.setOmuName(BscName.substring(0, 6) + "_OMU_0");
            omu2.setOmuName(BscName.substring(0, 6) + "_OMU_1");
        } else {
            omu1.setOmuName(BscName.substring(0, 6) + "_OMU_1");
            omu2.setOmuName(BscName.substring(0, 6) + "_OMU_0");
        }
    }
}
