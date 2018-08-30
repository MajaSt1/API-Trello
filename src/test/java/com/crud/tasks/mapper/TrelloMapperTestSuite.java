package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest(){
        //
        TrelloBoardDto trelloBoardDto1= new TrelloBoardDto("boardDto1","1",new ArrayList<>());
        TrelloBoardDto trelloBoardDto2= new TrelloBoardDto("boardDto2","2",new ArrayList<>());

        List<TrelloBoardDto> trelloBoardDtos= new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto1);
        trelloBoardDtos.add(trelloBoardDto2);
        //
        List<TrelloBoard> testMapper=trelloMapper.mapToBoards(trelloBoardDtos);
        //
        assertEquals(2,testMapper.size());
    }
    @Test
    public void mapToBoardsDto(){
        //
        TrelloBoard trelloBoard1= new TrelloBoard("1","board1",new ArrayList<>());
        TrelloBoard trelloBoard2= new TrelloBoard("2","board2",new ArrayList<>());

        List<TrelloBoard> trelloBoards= new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        //
        List<TrelloBoardDto> testMapper= trelloMapper.mapToBoardsDto(trelloBoards);
        //
        assertEquals(2,testMapper.size());

    }
    @Test
    public void  mapToList(){
        //
        TrelloListDto trelloListDto1= new TrelloListDto("listDto1","1",false);

        List<TrelloListDto> trelloListDtos= new ArrayList<>();
        trelloListDtos.add(trelloListDto1);
        //
        List<TrelloList> trelloLists= trelloMapper.mapToList(trelloListDtos);
        //
        assertEquals(1,trelloLists.size());
        System.out.println(trelloLists);
    }
    @Test
    public void mapToListDto(){
        //
        TrelloList trelloList1= new TrelloList("1","list1",true);

        List<TrelloList> trelloLists= new ArrayList<>();
        trelloLists.add(trelloList1);
        //
        List<TrelloListDto> trelloListDtos= trelloMapper.mapToListDto(trelloLists);
        //
        assertEquals(1,trelloListDtos.size());
    }
    @Test
    public void mapToCardDto(){
        //
        TrelloCard trelloCard= new TrelloCard("Card1","card","card","1");
        //
        TrelloCardDto trelloCardDto= trelloMapper.mapToCardDto(trelloCard);
        //
        assertEquals("Card1",trelloCardDto.getName());
    }
    @Test
    public void mapToCard(){
        //
        TrelloCardDto trelloCardDto = new TrelloCardDto("CardDto1","CardDto","cardDto","1");
        //
        TrelloCard trelloCard= trelloMapper.mapToCard(trelloCardDto);
        //
        assertEquals("CardDto",trelloCard.getDescription());

    }
}
