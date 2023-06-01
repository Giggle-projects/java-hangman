package src.repository;

import static src.exception.ErrorCode.*;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import src.hangman.HangmanRound;

public class RoundRepository {
	private static RoundRepository roundRepository;	//singleton
	private Map<Integer, HangmanRound> roundDB;	// gameId,HangmanRound
	private RoundRepository(){
		roundDB =new TreeMap<>();
	}
	public static RoundRepository getInstance() {
		if (roundRepository == null) {
			roundRepository = new RoundRepository();
		}
		return roundRepository;
	}

	public boolean isEmpty(){
		return roundDB.isEmpty();
	}

	public void save(HangmanRound round){
		roundDB.put(round.getRoundId(),round);
	}

	public HangmanRound getByRoundId(Integer roundID) throws NoSuchElementException{
		if(!roundDB.containsKey(roundID)){
			throw new NoSuchElementException(ERROR_NO_SUCH_ROUND_ID.getMessage());
		}
		return roundDB.get(roundID);
	}
}
