package com.games.demo.connect4.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.games.demo.connect4.exception.Connect4NotFoundException;
import com.games.demo.connect4.model.Connect4Model;
import com.games.demo.connect4.model.NewGameModel;
import com.games.demo.connect4.service.Connect4ServiceImpl;
import com.games.demo.connect4.service.IConnect4Service;

public class Connect4ServiceTest {

	private IConnect4Service service = null;
	private static final int PLAYER1_ID = 1;
	private static final int PLAYER2_ID = 2;
	private static final int ROW_SIZE = 6;
	private static final int COL_SIZE = 7;
	
	@Before
	public void setUp() throws Exception {
		service = new Connect4ServiceImpl();
	}
	
	@After
	public void tearDown() throws Exception {
		service = null;
	}

	
	@Test
	public void testConnect4ServiceCreation() {
		assertNotNull(service);
	}
	
	@Test
	public void testNewGameCreation(){
	
		NewGameModel model = createNewGameModel();
		Connect4Model connect4Model = service.createNewGame(model);
		assertNotNull(connect4Model);
		assertTrue(connect4Model.getPlayer1Id() == PLAYER1_ID);
		assertTrue(connect4Model.getGameGrid().length == ROW_SIZE);
		assertTrue(connect4Model.getGameGrid()[0].length == COL_SIZE);
	}
	
	@Test
	public void testPlayForValidGameId() throws Connect4NotFoundException{
		
		NewGameModel newGameModel = createNewGameModel();
		Connect4Model model = service.createNewGame(newGameModel);		
		service.playGame(model.getGameId(), 1, PLAYER1_ID);
		assertTrue(model.getGameGrid()[ROW_SIZE-1][0] == 1);
	}
	
	@Test(expected=Connect4NotFoundException.class)
	public void testPlayForInValidGameId() throws Connect4NotFoundException{
		
		NewGameModel newGameModel = createNewGameModel();
		service.createNewGame(newGameModel);		
		service.playGame("99", 1, PLAYER1_ID); //Invalid game id.
		
	}
	
	@Test
	public void testWin() throws Connect4NotFoundException{
		
		NewGameModel newGameModel = createNewGameModel();
		Connect4Model model = service.createNewGame(newGameModel);
		String gameId = model.getGameId();
		service.playGame(gameId, 1, PLAYER1_ID);
		service.playGame(gameId, 1, PLAYER2_ID);
		service.playGame(gameId, 2, PLAYER1_ID);
		service.playGame(gameId, 2, PLAYER2_ID);	
		service.playGame(gameId, 3, PLAYER1_ID);
		service.playGame(gameId, 3, PLAYER2_ID);
		service.playGame(gameId, 4, PLAYER1_ID);
		
		assertTrue(model.isGameOver());
		assertTrue(model.getWinnerId() == PLAYER1_ID);
		
		Connect4Model newModel = service.viewGame(gameId);
		assertTrue(newModel.isGameOver());
		assertTrue(newModel.getWinnerId() == PLAYER1_ID);
	}
	
	@Test(expected=Connect4NotFoundException.class)
	public void testDropInInvalidColumn() throws Connect4NotFoundException{
		
		NewGameModel newGameModel = createNewGameModel();
		Connect4Model model = service.createNewGame(newGameModel);
		String gameId = model.getGameId();
		service.playGame(gameId, 15, PLAYER1_ID);
		
		
		
	}
	
	private static NewGameModel createNewGameModel() {
		return new NewGameModel(PLAYER1_ID, PLAYER2_ID,ROW_SIZE, COL_SIZE);
	}
	
}
