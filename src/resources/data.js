function boards(){
var ret =
[
new Board(
  [
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','O','R','G','B','P'),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece(),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','O','R','G','B','P'),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('G','R','P','B','Y','O'),
      new Piece()
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','O','R','G','B','P'),
      new Piece()
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('G','R','P','B','Y','O'),
      new Piece('B','Y','G','P','O','R')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece(),
      new Piece('B','O','Y','G','P','R')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('G','R','P','B','Y','O'),
      new Piece('B','Y','G','P','O','R')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece(),
      new Piece('G','B','Y','R','P','O'),
      new Piece('B','O','Y','G','P','R')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('G','R','P','B','Y','O'),
      new Piece('B','Y','G','P','O','R')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('B','O','Y','G','P','R')
    ],
    [
      new Piece(),
      new Piece('G','R','P','B','Y','O'),
      new Piece('B','Y','G','P','O','R')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('B','O','Y','G','P','R')
    ],
    [
      new Piece('O','P','R','Y','B','G'),
      new Piece('G','R','P','B','Y','O'),
      new Piece('B','Y','G','P','O','R')
    ],
    [
      new Piece(),
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('B','O','Y','G','P','R')
    ],
    [
      new Piece('O','P','R','Y','B','G'),
      new Piece('G','R','P','B','Y','O'),
      new Piece('B','Y','G','P','O','R')
    ],
    [
      new Piece('O','B','P','R','Y','G'),
      new Piece(),
      new Piece('Y','R','G','B','O','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('B','O','Y','G','P','R')
    ],
    [
      new Piece('O','P','R','Y','B','G'),
      new Piece('G','R','P','B','Y','O'),
      new Piece('B','Y','G','P','O','R')
    ],
    [
      new Piece('O','B','P','R','Y','G'),
      new Piece('G','R','P','B','Y','O'),
      new Piece()
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('B','O','Y','G','P','R')
    ],
    [
      new Piece('O','P','R','Y','B','G'),
      new Piece('G','R','P','B','Y','O'),
      new Piece()
    ],
    [
      new Piece('O','B','P','R','Y','G'),
      new Piece('G','R','P','B','Y','O'),
      new Piece('P','B','G','R','O','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('B','O','Y','G','P','R')
    ],
    [
      new Piece('O','P','R','Y','B','G'),
      new Piece(),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('O','B','P','R','Y','G'),
      new Piece('G','R','P','B','Y','O'),
      new Piece('P','B','G','R','O','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('B','O','Y','G','P','R')
    ],
    [
      new Piece(),
      new Piece('B','P','O','Y','G','R'),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('O','B','P','R','Y','G'),
      new Piece('G','R','P','B','Y','O'),
      new Piece('P','B','G','R','O','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece(),
      new Piece('G','B','Y','R','P','O'),
      new Piece('B','O','Y','G','P','R')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('B','P','O','Y','G','R'),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('O','B','P','R','Y','G'),
      new Piece('G','R','P','B','Y','O'),
      new Piece('P','B','G','R','O','Y')
    ]
  ]
)

];

return ret;
}