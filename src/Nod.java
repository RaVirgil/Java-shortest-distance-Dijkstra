public class Nod {
    int nr;
    int x;
    int y;
    Nod(int nr,int x,int y) {
        this.nr =nr;
        this.x=x;
        this.y=y;
    }
    @Override
    public int hashCode() {
        return nr;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Nod other = (Nod) obj;
        return nr == other.nr;
    }
    public String toString() {
        return nr+" ";
    }

}
