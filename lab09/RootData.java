import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;
import java.util.Objects;


@XmlRootElement(name = "bip.poznan.pl")
class Root {
    @XmlElement(name = "data")
    private Data dataElemnt;

    public Data getData() {
        return dataElemnt;
    }

    public void setData(Data data) {
        this.dataElemnt = data;
    }

    @Override
    public String toString() {
        return "Root{" +
                "data=" + dataElemnt +
                '}';
    }
}

class Data {
    @XmlElement(name = "karty_informacyjne")
    private RootData rootDataElement;

    public RootData getRootData() {
        return rootDataElement;
    }

    public void setRootData(RootData rootData) {
        this.rootDataElement = rootData;
    }

    @Override
    public String toString() {
        return "Data{" +
                "rootData=" + rootDataElement +
                '}';
    }
}
@XmlRootElement(name = "karty_informacyjne")
class RootData {
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "karta_informacyjna")
    private List<KartaInformacyjna> kartyInformacyjne;


    @Override
    public String toString() {
        return "RootData{" +
                "kartyInformacyjne=" + kartyInformacyjne +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RootData rootData = (RootData) o;
        return Objects.equals(kartyInformacyjne, rootData.kartyInformacyjne);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kartyInformacyjne);
    }
}
