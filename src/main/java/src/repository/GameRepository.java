package src.repository;

import static src.exception.ErrorCode.*;

import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

import src.hangman.HangmanGame;

public class GameRepository {
	private static GameRepository gameRepository;	//singleton
	private SortedMap<Integer,HangmanGame> gameDB;	// gameId,HangmanGame
	private GameRepository(){
		gameDB =new TreeMap<>();
	}
	public static GameRepository getInstance() {
		if (gameRepository == null) {
			gameRepository = new GameRepository();
		}
		return gameRepository;
	}

	public boolean isEmpty(){
		return gameDB.isEmpty();
	}
	public void save(HangmanGame hangmanGame){
		gameDB.put(hangmanGame.getGameId(),hangmanGame);
	}

	public HangmanGame getByGameId(Integer gameID) throws NoSuchElementException{
		if(!gameDB.containsKey(gameID)){
			throw new NoSuchElementException(ERROR_NO_SUCH_GAME_ID.getMessage());
		}
		return gameDB.get(gameID);
	}
}
