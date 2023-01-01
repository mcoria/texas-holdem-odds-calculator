package poker;

import org.junit.Test;

import static poker.juegos.Juego.Tipo.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JuegoTest {

    @Test
    public void testCompararNada(){
        assertTrue(NADA.compareTo(NADA) == 0 );

        assertTrue(NADA.compareTo(PAR_SIMPLE) < 0 );
        assertTrue(NADA.compareTo(PAR_DOBLE) < 0 );
        assertTrue(NADA.compareTo(PIERNA) < 0 );
        assertTrue(NADA.compareTo(ESCALERA) < 0 );
        assertTrue(NADA.compareTo(COLOR) < 0 );
        assertTrue(NADA.compareTo(FULL) < 0 );
        assertTrue(NADA.compareTo(POKER) < 0 );
        assertTrue(NADA.compareTo(ESCALERA_COLOR) < 0 );

        assertTrue(PAR_SIMPLE.compareTo(NADA) > 0 );
        assertTrue(PAR_DOBLE.compareTo(NADA) > 0 );
        assertTrue(PIERNA.compareTo(NADA) > 0 );
        assertTrue(ESCALERA.compareTo(NADA) > 0 );
        assertTrue(COLOR.compareTo(NADA) > 0 );
        assertTrue(FULL.compareTo(NADA) > 0 );
        assertTrue(POKER.compareTo(NADA) > 0 );
        assertTrue(ESCALERA_COLOR.compareTo(NADA) > 0 );
    }

    @Test
    public void testCompararParSimple(){
        assertTrue(PAR_SIMPLE.compareTo(PAR_SIMPLE) == 0 );

        assertTrue(PAR_SIMPLE.compareTo(NADA) > 0 );
        assertTrue(PAR_SIMPLE.compareTo(PAR_DOBLE) < 0 );
        assertTrue(PAR_SIMPLE.compareTo(PIERNA) < 0 );
        assertTrue(PAR_SIMPLE.compareTo(ESCALERA) < 0 );
        assertTrue(PAR_SIMPLE.compareTo(COLOR) < 0 );
        assertTrue(PAR_SIMPLE.compareTo(FULL) < 0 );
        assertTrue(PAR_SIMPLE.compareTo(POKER) < 0 );
        assertTrue(PAR_SIMPLE.compareTo(ESCALERA_COLOR) < 0 );

        assertTrue(NADA.compareTo(PAR_SIMPLE) < 0 );
        assertTrue(PAR_DOBLE.compareTo(PAR_SIMPLE) > 0 );
        assertTrue(PIERNA.compareTo(PAR_SIMPLE) > 0 );
        assertTrue(ESCALERA.compareTo(PAR_SIMPLE) > 0 );
        assertTrue(COLOR.compareTo(PAR_SIMPLE) > 0 );
        assertTrue(FULL.compareTo(PAR_SIMPLE) > 0 );
        assertTrue(POKER.compareTo(PAR_SIMPLE) > 0 );
        assertTrue(ESCALERA_COLOR.compareTo(PAR_SIMPLE) > 0 );
    }

}
