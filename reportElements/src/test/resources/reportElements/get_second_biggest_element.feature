# Created by pbednarz at 4/21/19
Feature: Get second biggest element of the report
  As a business owner (user) I want a functionality that returns
  second biggest element from a report containing integer elements.
  If there are other data types in the report I want to get proper message.

  Scenario Outline: Positive verification - report contains only integer elements
    Given Report contains following elements
      | <e1> | <e2> | <e3> | <e4> | <e5> | <e6> |

    When User searches for second biggest element
    Then User should get <expectedElement>

    Examples:
      | e1 | e2 | e3 | e4 | e5 | e6 | expectedElement |
      | -2 | -3 | -3 | 0  | 0  | 6  | 0               |
      | -1 | 0  | 1  | 3  | 4  | 4  | 3               |
      | -5 | 1  | -4 | 3  | 8  | 12 | 8               |


  Scenario Outline: Report contains only one unique integer element (no second value)
    Given Report contains following elements
      | <e1> | <e2> | <e3> | <e4> | <e5> | <e6> |

    When User searches for second biggest element
    Then User should get "<expectedAnswer>"

    Examples:
      | e1 | e2 | e3 | e4 | e5 | e6 | expectedAnswer            |
      | 2  | 2  |  2 | 2  | 2  | 2  | No second biggest element |
      | -1 | -1 | -1 | -1 | -1 | -1 | No second biggest element |


  Scenario Outline: Report contains only one element
    Given Report contains following elements
      | <e1> |

    When User searches for second biggest element
    Then User should get "<expectedAnswer>"

    Examples:
      | e1    | expectedAnswer                     |
      | 2     | No second biggest element          |
      | -5.22 | Wrong data type - integer required |
      |       | Report contains empty elements     |


  Scenario Outline: Exception - report contains non integer or empty elements
    Given Report contains following elements
      | <e1> | <e2> | <e3> | <e4> | <e5> | <e6> |

    When User searches for second biggest element
    Then User should get "<expectedAnswer>"

    Examples:
      | e1        | e2        | e3        | e4        | e5        | e6        | expectedAnswer                     |
      | -2.1      | 0.3       | -3        | -3        | 10.6      | abcdEF    | Wrong data type - integer required |
      | -1.21     | B         | 1.92      | Zzz       | 8.3       | 4.54      | Wrong data type - integer required |
      |           |           |           |           |           |           | Report contains empty elements     |
      | 0         | -5        |           | 4         | 3         | 12        | Report contains empty elements     |
