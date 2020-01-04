package com.crud.tasks.mapper;


import com.crud.tasks.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrelloMappreTestSuite {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name1", "description1", "pos 1", "1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("name1", trelloCardDto.getName());
        assertEquals("description1", trelloCardDto.getDescription());
        assertEquals("pos 1", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getIdList());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name2", "desc2", "pos 2", "2");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("name2", trelloCard.getName());
        assertEquals("desc2", trelloCard.getDescription());
        assertEquals("pos 2", trelloCard.getPos());
        assertEquals("2", trelloCard.getListId());
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        TrelloList list1 = new TrelloList("1", "name1", true);
        TrelloList list2 = new TrelloList("2", "name2", false);
        TrelloList list3 = new TrelloList("3", "name3", true);

        List<TrelloList> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        //When
        List<TrelloListDto> listDtos = trelloMapper.mapToListsDto(lists);
        TrelloListDto listDto1 = listDtos.get(0);
        //Then
        assertEquals(3, listDtos.size());
        assertEquals("1", listDto1.getId());
        assertEquals("name1", listDto1.getName());
        assertTrue(listDto1.isClosed());
    }

    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto listDto1 = new TrelloListDto("1", "name1", true);
        TrelloListDto listDto2 = new TrelloListDto("2", "name2", true);
        TrelloListDto listDto3 = new TrelloListDto("3", "name3", false);

        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(listDto1);
        listDtos.add(listDto2);
        listDtos.add(listDto3);

        //When
        List<TrelloList> list = trelloMapper.mapToLists(listDtos);
        TrelloList list3 = list.get(2);

        //Then
        assertEquals(3, list.size());
        assertEquals("3", list3.getId());
        assertEquals("name3", list3.getName());
        assertFalse(list3.isClosed());
    }

    @Test
    public void mapToBoardsDto(){
        //Given
        TrelloList list1 = new TrelloList("1", "name1", true);
        TrelloList list2 = new TrelloList("2", "name2", false);
        TrelloList list3 = new TrelloList("3", "name3", true);
        TrelloList list4 = new TrelloList("4", "name4", false);
        TrelloList list5 = new TrelloList("5", "name5", true);

        List<TrelloList> trelloLists1 = new ArrayList<>();
        List<TrelloList> trelloLists2 = new ArrayList<>();
        trelloLists1.add(list1);
        trelloLists1.add(list2);
        trelloLists1.add(list3);
        trelloLists2.add(list4);
        trelloLists2.add(list5);

        TrelloBoard board1 = new TrelloBoard("1", "name1", trelloLists1);
        TrelloBoard board2 = new TrelloBoard("2", "name2", trelloLists2);

        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(board1);
        boardList.add(board2);

        //When
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardsDto(boardList);
        TrelloBoardDto boardDto = trelloBoardDto.get(0);
        TrelloListDto trelloListDto = trelloBoardDto.get(0).getLists().get(0);

        //Then
        assertEquals(2, trelloBoardDto.size());
        assertEquals("1", boardDto.getId());
        assertEquals("name1", boardDto.getName());
        assertEquals("1", trelloListDto.getId());
        assertEquals("name1", trelloListDto.getName());
        assertTrue(trelloListDto.isClosed());
    }

    @Test
    public void mapToBoard(){
        //Given
        TrelloListDto list1 = new TrelloListDto("1", "name1", true);
        TrelloListDto list2 = new TrelloListDto("2", "name2", true);
        TrelloListDto list3 = new TrelloListDto("3", "name3", false);
        TrelloListDto list4 = new TrelloListDto("4", "name4", true);
        TrelloListDto list5 = new TrelloListDto("5", "name5", false);
        TrelloListDto list6 = new TrelloListDto("6", "name6", true);

        List<TrelloListDto> trelloListDtos1 = new ArrayList<>();
        List<TrelloListDto> trelloListDtos2 = new ArrayList<>();
        trelloListDtos1.add(list1);
        trelloListDtos1.add(list2);
        trelloListDtos1.add(list3);
        trelloListDtos2.add(list4);
        trelloListDtos2.add(list5);
        trelloListDtos2.add(list6);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "name1", trelloListDtos1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "name2", trelloListDtos2);

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto1);
        trelloBoardDtoList.add(trelloBoardDto2);

        //When
        List<TrelloBoard> trelloBoard = trelloMapper.mapToBoards(trelloBoardDtoList);
        TrelloBoard board = trelloBoard.get(1);

        //Then
        assertEquals(2, trelloBoard.size());
        assertEquals("2", board.getId());
        assertEquals("name2", board.getName());
        assertEquals("5", board.getLists().get(1).getId());
        assertEquals("name5", board.getLists().get(1).getName());
        assertFalse(board.getLists().get(1).isClosed());
    }
}
