import java.util.Objects;

class KartaInformacyjna {
    private String link;
    private String id;
    private String data;
    private String skrotOrganizacja;
    private String komponentSrodowiska;
    private String typKarty;
    private String rodzajKarty;
    private String nrWpisu;
    private String znakSprawy;
    private String daneWnioskodawcy;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSkrotOrganizacja() {
        return skrotOrganizacja;
    }

    public void setSkrotOrganizacja(String skrotOrganizacja) {
        this.skrotOrganizacja = skrotOrganizacja;
    }

    public String getKomponentSrodowiska() {
        return komponentSrodowiska;
    }

    public void setKomponentSrodowiska(String komponentSrodowiska) {
        this.komponentSrodowiska = komponentSrodowiska;
    }

    public String getTypKarty() {
        return typKarty;
    }

    public void setTypKarty(String typKarty) {
        this.typKarty = typKarty;
    }

    public String getRodzajKarty() {
        return rodzajKarty;
    }

    public void setRodzajKarty(String rodzajKarty) {
        this.rodzajKarty = rodzajKarty;
    }

    public String getNrWpisu() {
        return nrWpisu;
    }

    public void setNrWpisu(String nrWpisu) {
        this.nrWpisu = nrWpisu;
    }

    public String getZnakSprawy() {
        return znakSprawy;
    }

    public void setZnakSprawy(String znakSprawy) {
        this.znakSprawy = znakSprawy;
    }

    public String getDaneWnioskodawcy() {
        return daneWnioskodawcy;
    }

    public void setDaneWnioskodawcy(String daneWnioskodawcy) {
        this.daneWnioskodawcy = daneWnioskodawcy;
    }

    @Override
    public String toString() {
        return "KartaInformacyjna{" +
                "link='" + link + '\'' +
                ", id='" + id + '\'' +
                ", data='" + data + '\'' +
                ", skrotOrganizacja='" + skrotOrganizacja + '\'' +
                ", komponentSrodowiska='" + komponentSrodowiska + '\'' +
                ", typKarty='" + typKarty + '\'' +
                ", rodzajKarty='" + rodzajKarty + '\'' +
                ", nrWpisu='" + nrWpisu + '\'' +
                ", znakSprawy='" + znakSprawy + '\'' +
                ", daneWnioskodawcy='" + daneWnioskodawcy + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KartaInformacyjna that = (KartaInformacyjna) o;
        return Objects.equals(link, that.link) &&
                Objects.equals(id, that.id) &&
                Objects.equals(data, that.data) &&
                Objects.equals(skrotOrganizacja, that.skrotOrganizacja) &&
                Objects.equals(komponentSrodowiska, that.komponentSrodowiska) &&
                Objects.equals(typKarty, that.typKarty) &&
                Objects.equals(rodzajKarty, that.rodzajKarty) &&
                Objects.equals(nrWpisu, that.nrWpisu) &&
                Objects.equals(znakSprawy, that.znakSprawy) &&
                Objects.equals(daneWnioskodawcy, that.daneWnioskodawcy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, id, data, skrotOrganizacja, komponentSrodowiska, typKarty, rodzajKarty, nrWpisu, znakSprawy, daneWnioskodawcy);
    }
}
