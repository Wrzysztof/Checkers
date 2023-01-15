package com.example.checkers.server.model;

class GameLogicTest {

    private GameLogic gameLogic;
    private HashMap<Integer, PawnData> pawns;

    @BeforeEach
    void setUp() {

        gameLogic = mock(GameLogic.class);
        pawns = mock(HashMap.class);

        when(gameLogic.getName()).thenReturn("game");
        when(gameLogic.getBoardSize()).thenReturn(8);
        when(gameLogic.getPawn(anyInt())).thenCallRealMethod();
        when(gameLogic.getPawn(anyInt(), anyInt())).thenCallRealMethod();
        when(gameLogic.doMove(anyString(), anyString(), anyString(), anyString()))
                .thenCallRealMethod();
        when(gameLogic.isStarted()).thenReturn(true);

        doCallRealMethod().when(gameLogic).startGame();
    }

    @Test
    @DisplayName("Should return no when the pawn is not on the board")
    void doMoveWhenPawnIsNotOnTheBoardThenReturnNo() {

        when(gameLogic.getPawn(anyInt())).thenReturn(null);

        String result = gameLogic.doMove("1", "1", "1", "1");

        assertEquals("game 1 1 1 no", result);
    }

    @Test
    @DisplayName("Should return no when the player is not allowed to move")
    void doMoveWhenPlayerIsNotAllowedToMoveThenReturnNo() {

        PawnData pawn = mock(PawnData.class);
        when(pawn.getColor()).thenReturn(Color.WHITE);
        when(pawn.getKey()).thenReturn(1);
        when(pawn.getX()).thenReturn(1);
        when(pawn.getY()).thenReturn(1);
        when(pawns.get(1)).thenReturn(pawn);

        String result = gameLogic.doMove("2", "1", "2", "2");

        assertEquals("game 1 2 2 no no no no", result);
    }

    @Test
    @DisplayName("Should return no when the new position is already taken")
    void doMoveWhenNewPositionIsAlreadyTakenThenReturnNo() {

        PawnData pawn = mock(PawnData.class);
        when(pawn.getKey()).thenReturn(1);
        when(pawn.getColor()).thenReturn(Color.WHITE);
        when(pawn.getX()).thenReturn(1);
        when(pawn.getY()).thenReturn(1);
        when(pawn.isKing()).thenReturn(false);
        when(pawns.get(1)).thenReturn(pawn);

        when(gameLogic.getPawn(1, 1)).thenReturn(pawn);

        String result = gameLogic.doMove("1", "1", "1", "1");

        assertEquals("game 1 1 1 no", result);
    }

    @Test
    @DisplayName("Should return yes when the move is allowed and pawn becomes king")
    void doMoveWhenThePawnBecomesKingThenReturnYes() {

        PawnData pawn = mock(PawnData.class);
        when(pawn.getKey()).thenReturn(1);
        when(pawn.getX()).thenReturn(0);
        when(pawn.getY()).thenReturn(0);
        when(pawn.getColor()).thenReturn(Color.WHITE);
        when(pawn.isKing()).thenReturn(false);
        when(pawn.isAlive()).thenReturn(true);

        pawns.put(1, pawn);

        when(gameLogic.getPawn(1)).thenReturn(pawn);
        when(gameLogic.getPawn(0, 0)).thenReturn(pawn);

        when(gameLogic.moveWhite(pawn, 0, 7)).thenReturn("yes");
        when(gameLogic.ifPlayerChange(pawn)).thenReturn(true);

        String result = gameLogic.doMove("1", "1", "0", "7");

        assertEquals("game 1 0 7 yes yes yes yes", result);
    }

    @Test
    @DisplayName("Should return no when the move is not allowed")
    void doMoveWhenTheMoveIsNotAllowedThenReturnNo() {

        PawnData pawn = mock(PawnData.class);
        when(pawn.getColor()).thenReturn(Color.WHITE);
        when(pawn.getKey()).thenReturn(1);
        when(pawn.getX()).thenReturn(1);
        when(pawn.getY()).thenReturn(1);
        when(pawn.isKing()).thenReturn(false);
        when(pawn.isAlive()).thenReturn(true);

        pawns.put(1, pawn);

        when(gameLogic.moveWhite(any(), anyInt(), anyInt())).thenReturn("no");

        String result = gameLogic.doMove("1", "1", "2", "2");

        assertEquals("game 1 2 2 no no no no", result);
    }
}