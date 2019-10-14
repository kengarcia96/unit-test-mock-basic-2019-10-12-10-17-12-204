package posmachine;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PosMachineTest {

    @Test
    public void should_get_receipt_using_real_price_calculator() {
        //given
        PriceCalculator priceCalculator = new PriceCalculator();
        PosMachine posMachine = new PosMachine(priceCalculator);

        //when
        //then
        assertThrows(UnsupportedOperationException.class, () -> {
            posMachine.getReceipt("Sprite");
        });
    }

    @Test
    public void should_get_receipt_using_sub_price_calculator() {
        //given
        String product = "Sprite";
        PriceCalculator priceCalculator = new StubPriceCalculator();
        PosMachine posMachine = new PosMachine(priceCalculator);

        //when
        String result = posMachine.getReceipt(product);

        //then
        assertEquals("Name: " + product + ", Price: 10.0", result);
    }

    @Test
    public void should_get_receipt_using_real_price_calculator_with_mockito() {
        //given
        String product = "Sprite";
        double price = 10.0;
        PriceCalculator priceCalculator = Mockito.mock(PriceCalculator.class);
        PosMachine posMachine = new PosMachine(priceCalculator);

        //when
        when(priceCalculator.calculate(product)).thenReturn(price);
        String result = posMachine.getReceipt(product);

        //then
        assertEquals("Name: " + product + ", Price: " + price, result);
    }

    private class StubPriceCalculator extends PriceCalculator {
        @Override
        public double calculate(String productName) {
            return 10;
        }
    }
}
