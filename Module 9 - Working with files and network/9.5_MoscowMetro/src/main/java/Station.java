public class Station
{
   private String lineId;
   private String name;

   public Station(String lineId, String name)
   {
       this.lineId = lineId;
       this.name = name;
   }

    public String getLineId() {
        return lineId;
    }
    public String getName() {
        return name;
    }

//    @Override
//    public int compareTo(Station station) {
//        int lineComparison = lineId.compareTo(station.getLineId());
//        return lineComparison != 0 ? lineComparison : name.compareToIgnoreCase(station.getName());
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        return compareTo((Station) obj) == 0;
//    }
//
//    @Override
//    public String toString() {
//        return name;
//    }
}
