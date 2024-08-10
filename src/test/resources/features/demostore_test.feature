Feature: Wyszukiwanie produktu
  Scenario: Wyszukujemy Plaid Cotton Shirt
    Given wchodzimy na strone madison island
    When uzytkownik wpisuje w pole wyszukiwania "Plaid Cotton Shirt"
    And klika przycisk szukaj
    Then strona wyszukiwania zawiera "Plaid Cotton Shirt"