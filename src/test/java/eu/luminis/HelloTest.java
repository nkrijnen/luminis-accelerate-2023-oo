package eu.luminis;

import org.junit.jupiter.api.Test;

import static eu.luminis.Hello.sayHello;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloTest {

    @Test
    public void shouldSayHello() {
        assertEquals("Hello Harry!", sayHello("Harry"));
    }

}
