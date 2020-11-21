package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void testMapToBoards() {
        //Given
        List<TrelloListDto> trelloListDtoList1 = new ArrayList<>();
        List<TrelloListDto> trelloListDtoList2 = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "list3", true);
        TrelloListDto trelloListDto4 = new TrelloListDto("4", "list4", false);
        trelloListDtoList1.add(trelloListDto1);
        trelloListDtoList1.add(trelloListDto2);
        trelloListDtoList2.add(trelloListDto3);
        trelloListDtoList2.add(trelloListDto4);

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "board1", trelloListDtoList1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "board2", trelloListDtoList2);
        trelloBoardDtoList.add(trelloBoardDto1);
        trelloBoardDtoList.add(trelloBoardDto2);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(2, trelloBoards.size());
    }

    @Test
    void testMapToBoardsDto() {
        //Given
        List<TrelloList> trelloListArr1 = new ArrayList<>();
        List<TrelloList> trelloListArr2 = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("1", "list1", true);
        TrelloList trelloList2 = new TrelloList("2", "list2", false);
        TrelloList trelloList3 = new TrelloList("3", "list3", true);
        TrelloList trelloList4 = new TrelloList("4", "list4", false);
        trelloListArr1.add(trelloList1);
        trelloListArr1.add(trelloList2);
        trelloListArr2.add(trelloList3);
        trelloListArr2.add(trelloList4);

        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "board1", trelloListArr1);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "board2", trelloListArr2);
        trelloBoardList.add(trelloBoard1);
        trelloBoardList.add(trelloBoard2);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(2, trelloBoardDtoList.size());
    }

    @Test
    void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDtoList1 = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list2", false);
        trelloListDtoList1.add(trelloListDto1);
        trelloListDtoList1.add(trelloListDto2);


        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList1);

        //Then
        assertEquals(2, trelloLists.size());
    }

    @Test
    void testMapToListDto() {
        //Given
        List<TrelloList> trelloListArr1 = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("1", "list1", true);
        TrelloList trelloList2 = new TrelloList("2", "list2", false);
        trelloListArr1.add(trelloList1);
        trelloListArr1.add(trelloList2);

        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloListArr1);

        //Then
        assertEquals(2, trelloListDtoList.size());
    }

    @Test
    void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card1", "desc test", "top", "1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("card1", trelloCardDto.getName());
    }

    @Test
    void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card1", "desc test", "top", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("card1", trelloCard.getName());
    }
}