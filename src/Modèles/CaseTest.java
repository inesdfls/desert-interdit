package Modèles;

import org.junit.Test;
import static org.junit.Assert.*;

public class CaseTest {

    @Test
    public void testSetAndGetCoord() {
        Case c = new Case(0, Case.Type.OEIL, null, 2, 3);
        assertEquals(2, c.getCoord().getCol());
        assertEquals(3, c.getCoord().getLig());

        c.setCoord(5, 7);
        assertEquals(5, c.getCoord().getCol());
        assertEquals(7, c.getCoord().getLig());
    }

    @Test
    public void testAddAndRemoveJoueurs() {
        Joueurs j1 = new Joueurs("Clover");
        Joueurs j2 = new Joueurs("Jerry");
        Desert d = new Desert();

        Case c = new Case(0, Case.Type.OEIL, d, 2, 3);
        assertEquals(0, c.getJoueurs().size());

        c.addJoueurs(j1, d);
        assertEquals(1, c.getJoueurs().size());
        assertEquals(j1, c.getJoueurs().get(0));

        c.addJoueurs(j2, d);
        assertEquals(2, c.getJoueurs().size());
        assertEquals(j1, c.getJoueurs().get(0));
        assertEquals(j2, c.getJoueurs().get(1));

        c.removeJoueursC(j1, d);
        assertEquals(1, c.getJoueurs().size());
        assertEquals(j2, c.getJoueurs().get(0));
    }
}
