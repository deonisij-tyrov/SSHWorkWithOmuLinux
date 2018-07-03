public class Omu {
    private String bscName;
    private int omuSlot;
    private String omuExternalIp;
    private String omuName;

    public String getOmuName() {
        return omuName;
    }

    public void setOmuName(String omuName) {
        this.omuName = omuName;
    }

    public String getBscName() {
        return bscName;
    }

    public void setBscName(String bscName) {
        this.bscName = bscName;
    }

    public int getOmuSlot() {
        return omuSlot;
    }

    public void setOmuSlot(int omuSlot) {
        this.omuSlot = omuSlot;
    }

    public String getOmuExternalIp() {
        return omuExternalIp;
    }

    public void setOmuExternalIp(String omuExternalIp) {
        this.omuExternalIp = omuExternalIp;
    }

    @Override
    public String toString() {
        return "Omu{" +
                "bscName='" + bscName + '\'' +
                ", omuSlot=" + omuSlot +
                ", omuExternalIp='" + omuExternalIp + '\'' +
                ", omuName='" + omuName + '\'' +
                '}';
    }
}
