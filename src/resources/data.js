function boards(){
var ret =
[
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('Y','R','G','B','O','P'),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece(),
      new Piece('Y','G','B','O','R','P')
    ],
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P'),
      new Piece('Y','R','G','B','O','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece(),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('B','Y','G','P','O','R'),
      new Piece('Y','G','B','O','R','P')
    ],
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P'),
      new Piece('Y','R','G','B','O','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('R','O','P','G','Y','B'),
      new Piece()
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('B','Y','G','P','O','R'),
      new Piece('Y','G','B','O','R','P')
    ],
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P'),
      new Piece('Y','R','G','B','O','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('R','O','P','G','Y','B'),
      new Piece('G','P','B','Y','R','O')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('B','Y','G','P','O','R'),
      new Piece()
    ],
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P'),
      new Piece('Y','R','G','B','O','P')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('R','O','P','G','Y','B'),
      new Piece('G','P','B','Y','R','O')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('B','Y','G','P','O','R'),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P'),
      new Piece()
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('R','O','P','G','Y','B'),
      new Piece('G','P','B','Y','R','O')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('B','Y','G','P','O','R'),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece(),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('R','O','P','G','Y','B'),
      new Piece('G','P','B','Y','R','O')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('B','Y','G','P','O','R'),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece(),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('R','O','P','G','Y','B'),
      new Piece('G','P','B','Y','R','O')
    ],
    [
      new Piece(),
      new Piece('B','Y','G','P','O','R'),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('R','O','P','G','Y','B'),
      new Piece('G','P','B','Y','R','O')
    ],
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece(),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece(),
      new Piece('G','P','B','Y','R','O')
    ],
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece('G','R','P','B','Y','O'),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('B','P','O','Y','G','R'),
      new Piece()
    ],
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece('G','R','P','B','Y','O'),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('B','P','O','Y','G','R'),
      new Piece('P','B','G','R','O','Y')
    ],
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece('G','R','P','B','Y','O'),
      new Piece()
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('B','P','O','Y','G','R'),
      new Piece('P','B','G','R','O','Y')
    ],
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece(),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece(),
      new Piece('P','B','G','R','O','Y')
    ],
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece(),
      new Piece('O','R','Y','B','P','G'),
      new Piece('P','B','G','R','O','Y')
    ],
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('O','R','Y','B','P','G'),
      new Piece('P','B','G','R','O','Y')
    ],
    [
      new Piece(),
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('O','R','Y','B','P','G'),
      new Piece('P','B','G','R','O','Y')
    ],
    [
      new Piece('O','B','P','R','Y','G'),
      new Piece(),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece(),
      new Piece('P','B','G','R','O','Y')
    ],
    [
      new Piece('O','B','P','R','Y','G'),
      new Piece('B','O','Y','G','P','R'),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('G','B','Y','R','P','O'),
      new Piece()
    ],
    [
      new Piece('O','B','P','R','Y','G'),
      new Piece('B','O','Y','G','P','R'),
      new Piece('Y','R','G','B','O','P')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece('O','B','P','R','Y','G'),
      new Piece('B','O','Y','G','P','R'),
      new Piece()
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece('O','B','P','R','Y','G'),
      new Piece(),
      new Piece('P','O','B','G','R','Y')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece('O','B','P','R','Y','G'),
      new Piece('B','O','Y','G','P','R'),
      new Piece('P','O','B','G','R','Y')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece(),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('R','P','G','Y','O','B')
    ],
    [
      new Piece('O','B','P','R','Y','G'),
      new Piece('B','O','Y','G','P','R'),
      new Piece('P','O','B','G','R','Y')
    ],
    [
      new Piece(),
      new Piece('R','Y','O','P','G','B'),
      new Piece('O','R','Y','B','P','G')
    ]
  ]
)

];

return ret;
}