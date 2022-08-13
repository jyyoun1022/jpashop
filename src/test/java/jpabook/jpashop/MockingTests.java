package jpabook.jpashop;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockingTests {

    @Test
    public void mockingList(){
        List mockList = mock(ArrayList.class);

        mockList.add("apple");

        assertEquals("apple",mockList.get(0));
    }

    @Test
    public void mockingList_when(){
        List mockList = mock(ArrayList.class);

        when(mockList.get(0)).thenReturn("apple");
        when(mockList.get(1)).thenReturn("kiwi");
        when(mockList.size()).thenReturn(10);

        assertEquals("apple",mockList.get(0));
        assertEquals("kiwi",mockList.get(1));
        assertEquals(11,mockList.size());
    }

    @Test
    public void mockingList_verify(){
        List mockList = mock(ArrayList.class);

        mockList.add("apple");
        verify(mockList).add("apple");

        mockList.get(0);
        mockList.get(0);
        verify(mockList,times(2)).get(0);
        verify(mockList, atLeast(2)).get(0);


        ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);
        verify(mockList).add(arg.capture());
        assertEquals("apple",arg.getValue());
    }

}
