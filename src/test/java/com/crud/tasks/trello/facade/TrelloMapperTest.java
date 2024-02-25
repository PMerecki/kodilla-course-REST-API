package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TrelloMapperTest {

    private final TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void testMapToBoards() {
        // Given
        List<TrelloBoardDto> boardDtoList = new ArrayList<>();
        boardDtoList.add(new TrelloBoardDto("1", "Board 1", new ArrayList<>()));
        boardDtoList.add(new TrelloBoardDto("2", "Board 2", new ArrayList<>()));

        // When
        List<TrelloBoard> boardList = trelloMapper.mapToBoards(boardDtoList);

        // Then
        assertEquals(2, boardList.size());
        assertEquals("1", boardList.get(0).getId());
        assertEquals("Board 1", boardList.get(0).getName());
        assertEquals(0, boardList.get(0).getLists().size());
    }

    @Test
    public void testMapToBoardsDto() {
        // Given
        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(new TrelloBoard("1", "Board 1", new ArrayList<>()));
        boardList.add(new TrelloBoard("2", "Board 2", new ArrayList<>()));

        // When
        List<TrelloBoardDto> boardDtoList = trelloMapper.mapToBoardsDto(boardList);

        // Then
        assertEquals(2, boardDtoList.size());
        assertEquals("1", boardDtoList.get(0).getId());
        assertEquals("Board 1", boardDtoList.get(0).getName());
        assertEquals(0, boardDtoList.get(0).getLists().size());
    }

    @Test
    public void testMapToList() {
        // Given
        List<TrelloListDto> listDtoList = new ArrayList<>();
        listDtoList.add(new TrelloListDto("1", "List 1", false));
        listDtoList.add(new TrelloListDto("2", "List 2", true));

        // When
        List<TrelloList> listList = trelloMapper.mapToList(listDtoList);

        // Then
        assertEquals(2, listList.size());
        assertEquals("1", listList.get(0).getId());
        assertEquals("List 1", listList.get(0).getName());
        assertFalse(listList.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        // Given
        List<TrelloList> listList = new ArrayList<>();
        listList.add(new TrelloList("1", "List 1", false));
        listList.add(new TrelloList("2", "List 2", true));

        // When
        List<TrelloListDto> listDtoList = trelloMapper.mapToListDto(listList);

        // Then
        assertEquals(2, listDtoList.size());
        assertEquals("1", listDtoList.get(0).getId());
        assertEquals("List 1", listDtoList.get(0).getName());
        assertFalse(listDtoList.get(0).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        // Given
        TrelloCard trelloCard = new TrelloCard("Card 1", "Description 1", "pos1", "listId1");

        // When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        // Then
        assertEquals("Card 1", trelloCardDto.getName());
        assertEquals("Description 1", trelloCardDto.getDescription());
        assertEquals("pos1", trelloCardDto.getPos());
        assertEquals("listId1", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card 2", "Description 2", "pos2", "listId2");

        // When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        // Then
        assertEquals("Card 2", trelloCard.getName());
        assertEquals("Description 2", trelloCard.getDescription());
        assertEquals("pos2", trelloCard.getPos());
        assertEquals("listId2", trelloCard.getListId());
    }
}
