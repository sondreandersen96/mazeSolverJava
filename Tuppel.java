class Tuppel {
    private int x;
    private int y;
    
    public Tuppel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int hentX() {
        return this.x;
    }

    public int hentY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object other) {
        // Sjekker om objektene er de samme 
        if (this == other) return true;
        
        // Sjekker om other er en instans av samme klasse (Tuppel)
        if (!(other instanceof Tuppel)) {
            return false;
        }

        // Caster other til Tuppel for aa kunne bruke metoder fra Tuppel
        Tuppel o = (Tuppel) other;

        // Sammenlikning
        if (this.x == o.hentX() && this.y == o.hentY()) {
            return true;
        } else {
            return false;
        }
    }
}