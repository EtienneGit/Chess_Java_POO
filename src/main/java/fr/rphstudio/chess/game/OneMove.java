package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess.*;

/**
 * Represent one move that has been done, it is meant to be contain in a list
 */
public class OneMove {
    static int index = 0;

    private int indexItem;
    private Piece pieceMoved;
    private ChessPosition pieceMovedPos;
    private boolean hasEaten;
    private Piece pieceEaten;
    private ChessPosition pieceEatenPos;
    private ChessPosition newPos;

    // Attribute used by the pawn morphing into queen
    private boolean isPromoted;

    // Attribute used by the rook movement
    private boolean isRook;

    public int getIndexItem() {
        return indexItem;
    }

    public OneMove(Piece pieceMoved, ChessPosition pieceMovedPos, Piece pieceEaten, ChessPosition pieceEatenPos) {
        this.indexItem = index;
        index++;
        this.hasEaten = true;
        this.pieceEaten = pieceEaten;
        this.pieceEatenPos = pieceEatenPos;
        this.pieceMoved = pieceMoved;
        this.pieceMovedPos = pieceMovedPos;
        this.isPromoted = checkIfPawnWillPromote();
    }

    public OneMove(Piece pieceMoved, ChessPosition pieceMovedPos, ChessPosition newPos) {
        this.indexItem = index;
        index++;
        this.hasEaten = false;
        this.pieceMoved = pieceMoved;
        this.pieceMovedPos = pieceMovedPos;
        this.newPos = newPos;
        this.isPromoted = checkIfPawnWillPromote();
    }

    // Constructor for rook move
    public OneMove(Piece pieceMoved, ChessPosition oldRookPos, boolean rook) {
        this.indexItem = index;
        index++;
        this.isRook = rook;
        this.pieceMovedPos = oldRookPos;
        this.pieceMoved = pieceMoved;
    }

    private boolean checkIfPawnWillPromote() {
        if (this.pieceMoved.getType() == ChessType.TYP_PAWN && (pieceMovedPos.y == 0 || pieceMovedPos.y == 7)) {
            return true;
        }
        return false;
    }

    public boolean undoMove(Board chessBoard) {
        if (this.isPromoted) {

        }
        else if (this.isRook) {

        }
        else if (this.hasEaten){
            chessBoard.replacingPiece(this.pieceMoved, this.pieceMovedPos);
            chessBoard.replacingPiece(this.pieceEaten, this.pieceEatenPos);
            return true;
        }
        else {
            chessBoard.replacingPiece(this.pieceMoved, this.pieceMovedPos);
            chessBoard.removeMovingPiece(this.newPos);
        }
        return false;
    }
}