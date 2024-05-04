package pl.pollub.integracja_projekt.Utils.XmlReader;

import java.util.List;

public class InterestRatesDTO {
    private String effectiveFrom;
    private List<EntryDTO> entryList;
    public InterestRatesDTO(){

    }
    public InterestRatesDTO(String effectiveFrom, List<EntryDTO> entryList){
        this.effectiveFrom = effectiveFrom;
        this.entryList = entryList;
    }

    public List<EntryDTO> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<EntryDTO> entryList) {
        this.entryList = entryList;
    }

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }
    public void show(){
        System.out.println("Obowiazuje od: " + effectiveFrom);
        entryList.forEach(entryDTO -> {
            entryDTO.show();
        });
        System.out.println();
    }

}
