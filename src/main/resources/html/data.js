function boards(){
var ret =
[
new Board(
  [
    [
      new Piece('O','P','R','Y','B','G'),
      new Piece('O','R','Y','B','P','G'),
      new Piece('O','P','R','Y','B','G')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece(),
      new Piece('O','P','R','Y','B','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('O','P','R','Y','B','G'),
      new Piece(),
      new Piece('O','P','R','Y','B','G')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('B','O','Y','G','P','R'),
      new Piece('O','P','R','Y','B','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('O','P','R','Y','B','G'),
      new Piece('R','P','G','Y','O','B'),
      new Piece()
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('B','O','Y','G','P','R'),
      new Piece('O','P','R','Y','B','G')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('O','P','R','Y','B','G'),
      new Piece('R','P','G','Y','O','B'),
      new Piece('P','G','R','O','B','Y')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('B','O','Y','G','P','R'),
      new Piece()
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('O','P','R','Y','B','G'),
      new Piece('R','P','G','Y','O','B'),
      new Piece('P','G','R','O','B','Y')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece(),
      new Piece('P','O','B','G','R','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('O','P','R','Y','B','G'),
      new Piece('R','P','G','Y','O','B'),
      new Piece('P','G','R','O','B','Y')
    ],
    [
      new Piece(),
      new Piece('R','Y','O','P','G','B'),
      new Piece('P','O','B','G','R','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece(),
      new Piece('R','P','G','Y','O','B'),
      new Piece('P','G','R','O','B','Y')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('R','Y','O','P','G','B'),
      new Piece('P','O','B','G','R','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece(),
      new Piece('P','G','R','O','B','Y')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('R','Y','O','P','G','B'),
      new Piece('P','O','B','G','R','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece('R','G','Y','O','P','B'),
      new Piece()
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('R','Y','O','P','G','B'),
      new Piece('P','O','B','G','R','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece('R','G','Y','O','P','B'),
      new Piece('O','Y','B','P','R','G')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece('R','Y','O','P','G','B'),
      new Piece()
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece('R','G','Y','O','P','B'),
      new Piece('O','Y','B','P','R','G')
    ],
    [
      new Piece('Y','O','R','G','B','P'),
      new Piece(),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','P','B','Y','R','O'),
      new Piece('R','G','Y','O','P','B'),
      new Piece('O','Y','B','P','R','G')
    ],
    [
      new Piece(),
      new Piece('B','O','Y','G','P','R'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece(),
      new Piece('R','G','Y','O','P','B'),
      new Piece('O','Y','B','P','R','G')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('B','O','Y','G','P','R'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece(),
      new Piece('O','Y','B','P','R','G')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('B','O','Y','G','P','R'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('O','R','Y','B','P','G'),
      new Piece('O','Y','B','P','R','G')
    ],
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece(),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','G','B','O','R','P'),
      new Piece('O','R','Y','B','P','G'),
      new Piece('O','Y','B','P','R','G')
    ],
    [
      new Piece(),
      new Piece('R','G','Y','O','P','B'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece(),
      new Piece('O','R','Y','B','P','G'),
      new Piece('O','Y','B','P','R','G')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('R','G','Y','O','P','B'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece(),
      new Piece('O','Y','B','P','R','G')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('R','G','Y','O','P','B'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('B','Y','G','P','O','R'),
      new Piece()
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('R','G','Y','O','P','B'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('B','Y','G','P','O','R'),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece('R','G','Y','O','P','B'),
      new Piece()
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('B','Y','G','P','O','R'),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('O','Y','B','P','R','G'),
      new Piece(),
      new Piece('P','G','R','O','B','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','R','G','B','O','P'),
      new Piece('B','Y','G','P','O','R'),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece(),
      new Piece('R','Y','O','P','G','B'),
      new Piece('P','G','R','O','B','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece(),
      new Piece('B','Y','G','P','O','R'),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('B','Y','G','P','O','R'),
      new Piece('R','Y','O','P','G','B'),
      new Piece('P','G','R','O','B','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece(),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('B','Y','G','P','O','R'),
      new Piece('R','Y','O','P','G','B'),
      new Piece('P','G','R','O','B','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('B','Y','G','P','O','R'),
      new Piece(),
      new Piece('P','G','R','O','B','Y')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','O','R','G','B','P')
    ],
    [
      new Piece('B','Y','G','P','O','R'),
      new Piece('R','G','Y','O','P','B'),
      new Piece()
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece('Y','B','O','R','G','P'),
      new Piece()
    ],
    [
      new Piece('B','Y','G','P','O','R'),
      new Piece('R','G','Y','O','P','B'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece(),
      new Piece('G','B','Y','R','P','O')
    ],
    [
      new Piece('B','Y','G','P','O','R'),
      new Piece('R','G','Y','O','P','B'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('G','B','Y','R','P','O')
    ],
    [
      new Piece('B','Y','G','P','O','R'),
      new Piece(),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('G','Y','R','P','B','O'),
      new Piece('G','B','Y','R','P','O'),
      new Piece('G','B','Y','R','P','O')
    ],
    [
      new Piece(),
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece(),
      new Piece('G','B','Y','R','P','O'),
      new Piece('G','B','Y','R','P','O')
    ],
    [
      new Piece('P','G','R','O','B','Y'),
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece(),
      new Piece('G','B','Y','R','P','O')
    ],
    [
      new Piece('P','G','R','O','B','Y'),
      new Piece('O','Y','B','P','R','G'),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','G','B','O','R','P'),
      new Piece('G','B','Y','R','P','O')
    ],
    [
      new Piece('P','G','R','O','B','Y'),
      new Piece(),
      new Piece('G','Y','R','P','B','O')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','G','B','O','R','P'),
      new Piece('G','B','Y','R','P','O')
    ],
    [
      new Piece('P','G','R','O','B','Y'),
      new Piece('R','Y','O','P','G','B'),
      new Piece()
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','G','B','O','R','P'),
      new Piece()
    ],
    [
      new Piece('P','G','R','O','B','Y'),
      new Piece('R','Y','O','P','G','B'),
      new Piece('R','G','Y','O','P','B')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece(),
      new Piece('R','G','Y','O','P','B')
    ],
    [
      new Piece('P','G','R','O','B','Y'),
      new Piece('R','Y','O','P','G','B'),
      new Piece('R','G','Y','O','P','B')
    ]
  ]
)
,
new Board(
  [
    [
      new Piece('Y','B','O','R','G','P'),
      new Piece('Y','B','O','R','G','P'),
      new Piece('R','G','Y','O','P','B')
    ],
    [
      new Piece('P','G','R','O','B','Y'),
      new Piece(),
      new Piece('R','G','Y','O','P','B')
    ]
  ]
)

];

return ret;
}