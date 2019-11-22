public class Main {

    public static void main(String[] args) {
        PhysicalPersonClient clientP1 = new PhysicalPersonClient();
        clientP1.enroll(5000);
        clientP1.getSettlementAccount();
        clientP1.withDrawal(456.23);
        clientP1.getSettlementAccount();
        System.out.println("---------------");

        LegalPersonClient clientL1 = new LegalPersonClient();
        clientL1.enroll(1231);
        clientL1.getSettlementAccount();
        clientL1.withDrawal( 123);
        clientL1.getSettlementAccount();
        System.out.println("-----------------");

        IndividualBusinessmanClient clientI1 = new IndividualBusinessmanClient();
        clientI1.enroll(1000);
        clientI1.getSettlementAccount();
        clientI1.enroll(500);
        clientI1.getSettlementAccount();
        clientI1.withDrawal(341);
        clientI1.getSettlementAccount();
    }
}
