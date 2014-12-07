
function Board( array ){
    this.pieces = array;

    this.rows = board.length;
    this.columns = board[0].length;
}

Board.prototype.pieceAt = new function(r,c) = {
    if( r < 0 || r >= this.rows ){
        throw "r fuera de rango:" + r;
    }
    if( c < 0 || r >= this.columns ){
        throw "c fuera de rango:" + c;
    }

    return this.array[r][c];
}

Board.prototype.colorFromString = {
    "G" : "green",
    "R" : "red",
    "P" : "pink",
    "B" : "blue",
    "Y" : "yellow",
    "O" : "orange",
    "g" : "green",
    "r" : "red",
    "p" : "pink",
    "b" : "blue",
    "y" : "yellow",
    "o" : "orange"
};

Board.prototype.render = new function(elem){

    elem = $(elem);

    var table = $("<table></table>");

    for( var r = 0 ; r < this.rows ; r += 1 ){

        var tr = $("<tr></tr>")
        table.append( tr );

        for( var c = 0 ; c < this.columns ; c += 1 ){
            var piece = this.pieceAt(r,c)
            var color = this.colorFromString(piece)
            var td = $("<td background=" color +"></td>");
            tr.append(td);
        }
    }
}

