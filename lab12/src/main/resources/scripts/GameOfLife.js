var initialize = function(board, rows, cols) {
    for (var i = 0; i < rows; i++) {
        board[i] = new Array(cols);
        for (var j = 0; j < cols; j++) {
            board[i][j] = Math.random() > 0.5 ? 1 : 0;
        }
    }
    return board;
};

var update = function(board, rows, cols) {
    var nextBoard = new Array(rows);
    for (var i = 0; i < rows; i++) {
        nextBoard[i] = new Array(cols);
        for (var j = 0; j < cols; j++) {
            var neighbors = countNeighbors(board, rows, cols, i, j);

            if (board[i][j] === 1) {
                if (neighbors < 2 || neighbors > 3) {
                    nextBoard[i][j] = 0;
                } else {
                    nextBoard[i][j] = 1;
                }
            } else {
                if (neighbors === 3) {
                    nextBoard[i][j] = 1;
                } else {
                    nextBoard[i][j] = 0;
                }
            }
        }
    }

    for (var i = 0; i < rows; i++) {
        for (var j = 0; j < cols; j++) {
            board[i][j] = nextBoard[i][j];
        }
    }
};

var render = function(board, rows, cols) {
    for (var i = 0; i < rows; i++) {
        var rowString = "";
        for (var j = 0; j < cols; j++) {
            if (board[i][j] === 1) {
                rowString += "O ";
            } else {
                rowString += "X ";
            }
        }
        print(rowString);
    }
};

var unload = function(board) {
    board = [];
};

var countNeighbors = function(board, rows, cols, row, col) {
    var count = 0;

    for (var i = -1; i <= 1; i++) {
        for (var j = -1; j <= 1; j++) {
            var neighborRow = row + i;
            var neighborCol = col + j;

            if (i === 0 && j === 0) {
                continue;
            }

            if (
                neighborRow >= 0 &&
                neighborRow < rows &&
                neighborCol >= 0 &&
                neighborCol < cols
            ) {
                count += board[neighborRow][neighborCol];
            }
        }
    }

    return count;
};