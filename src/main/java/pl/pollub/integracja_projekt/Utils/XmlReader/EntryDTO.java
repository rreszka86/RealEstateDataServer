package pl.pollub.integracja_projekt.Utils.XmlReader;

public class EntryDTO {
    private String id;
    private String interestPercentage;
    public EntryDTO(){

    }
    public EntryDTO(String id, String interestPercentage){
        this.id = id;
        this.interestPercentage = interestPercentage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterestPercentage() {
        return interestPercentage.replace(",",".");
    }

    public void setInterestPercentage(String interestPercentage) {
        this.interestPercentage = interestPercentage;
    }
    public void show(){
        System.out.print(" " + id + " " + interestPercentage);
        System.out.println();
    }
}