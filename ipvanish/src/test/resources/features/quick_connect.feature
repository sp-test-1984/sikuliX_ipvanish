Feature: quick connect
    Scenario Outline: IPVanish should allow user to connect using different protocols
        Given IPVanish is accessible
        And I am logged
        When I select "<protocol>"
        And attempt connection
        Then I should successfully connect


 Examples: protocol permutations
    | protocol |
    #| ikev2    |
    #| tcp      |
    #| ipsec    |
    | l2tp     |
    #| udp      |