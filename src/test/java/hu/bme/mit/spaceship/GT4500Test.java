package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;

  private TorpedoStore mockedTorpedoStore1;
  private TorpedoStore mockedTorpedoStore2;

  @BeforeEach
  public void init(){
    this.mockedTorpedoStore1 = mock(TorpedoStore.class);
    this.mockedTorpedoStore2 = mock(TorpedoStore.class);
    this.ship = new GT4500(this.mockedTorpedoStore1, this.mockedTorpedoStore2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockedTorpedoStore1.fire(1)).thenReturn(true);
    when(mockedTorpedoStore1.isEmpty()).thenReturn(false);

    when(mockedTorpedoStore2.fire(1)).thenReturn(true);
    when(mockedTorpedoStore2.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockedTorpedoStore1, times(1)).isEmpty();
    verify(mockedTorpedoStore1, times(1)).fire(1);
    verify(mockedTorpedoStore2, times(0)).isEmpty();
    verify(mockedTorpedoStore2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockedTorpedoStore1.fire(1)).thenReturn(true);
    when(mockedTorpedoStore1.isEmpty()).thenReturn(false);

    when(mockedTorpedoStore2.fire(1)).thenReturn(true);
    when(mockedTorpedoStore2.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockedTorpedoStore1, times(1)).isEmpty();
    verify(mockedTorpedoStore1, times(1)).fire(1);
    verify(mockedTorpedoStore2, times(1)).isEmpty();
    verify(mockedTorpedoStore2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_PrimaryEmpty() {
    // Arrange
    when(mockedTorpedoStore1.fire(1)).thenReturn(false);
    when(mockedTorpedoStore1.isEmpty()).thenReturn(true);
    when(mockedTorpedoStore2.fire(1)).thenReturn(true);
    when(mockedTorpedoStore2.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockedTorpedoStore1, times(1)).isEmpty();
    verify(mockedTorpedoStore1, times(0)).fire(1);
    verify(mockedTorpedoStore2, times(1)).isEmpty();
    verify(mockedTorpedoStore2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_BothEmpty() {
    // Arrange
    when(mockedTorpedoStore1.fire(1)).thenReturn(false);
    when(mockedTorpedoStore1.isEmpty()).thenReturn(true);
    when(mockedTorpedoStore2.fire(1)).thenReturn(false);
    when(mockedTorpedoStore2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockedTorpedoStore1, times(1)).isEmpty();
    verify(mockedTorpedoStore1, times(0)).fire(1);
    verify(mockedTorpedoStore2, times(1)).isEmpty();
    verify(mockedTorpedoStore2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Failed(){
    // Arrange
    when(mockedTorpedoStore1.fire(1)).thenReturn(false);
    when(mockedTorpedoStore1.isEmpty()).thenReturn(true);
    when(mockedTorpedoStore2.fire(1)).thenReturn(false);
    when(mockedTorpedoStore2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockedTorpedoStore1, times(1)).isEmpty();
    verify(mockedTorpedoStore1, times(0)).fire(1);
    verify(mockedTorpedoStore2, times(1)).isEmpty();
    verify(mockedTorpedoStore2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_TwoFromFirst(){
    // Arrange
    when(mockedTorpedoStore1.fire(1)).thenReturn(true);
    when(mockedTorpedoStore1.isEmpty()).thenReturn(false);
    when(mockedTorpedoStore2.fire(1)).thenReturn(false);
    when(mockedTorpedoStore2.isEmpty()).thenReturn(true);

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result1);
    assertEquals(true, result2);
    verify(mockedTorpedoStore1, times(2)).isEmpty();
    verify(mockedTorpedoStore1, times(2)).fire(1);
    verify(mockedTorpedoStore2, times(1)).isEmpty();
    verify(mockedTorpedoStore2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Two(){
    // Arrange
    when(mockedTorpedoStore1.fire(1)).thenReturn(true);
    when(mockedTorpedoStore1.isEmpty()).thenReturn(false);
    when(mockedTorpedoStore2.fire(1)).thenReturn(true);
    when(mockedTorpedoStore2.isEmpty()).thenReturn(false);

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result1);
    assertEquals(true, result2);
    verify(mockedTorpedoStore1, times(1)).isEmpty();
    verify(mockedTorpedoStore1, times(1)).fire(1);
    verify(mockedTorpedoStore2, times(1)).isEmpty();
    verify(mockedTorpedoStore2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_TwoOnlyFirstSuccess(){
    // Arrange
    when(mockedTorpedoStore1.fire(1)).thenReturn(true);
    when(mockedTorpedoStore1.isEmpty()).thenReturn(false);
    when(mockedTorpedoStore2.fire(1)).thenReturn(false);
    when(mockedTorpedoStore2.isEmpty()).thenReturn(true);
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);

    when(mockedTorpedoStore1.fire(1)).thenReturn(false);
    when(mockedTorpedoStore1.isEmpty()).thenReturn(true);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result1);
    assertEquals(false, result2);
    verify(mockedTorpedoStore1, times(2)).isEmpty();
    verify(mockedTorpedoStore1, times(1)).fire(1);
    verify(mockedTorpedoStore2, times(1)).isEmpty();
    verify(mockedTorpedoStore2, times(0)).fire(1);
  }

}
