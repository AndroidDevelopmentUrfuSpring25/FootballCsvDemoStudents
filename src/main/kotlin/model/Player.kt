package model
//Name;Team;City;Position;Nationality;Agency;Transfer cost;Participations;Goals;Assists;Yellow cards;Red cards
// Iva Streich;Nevada whales;South Carolina;MIDFIELD;Colombia;D'Amore LLC;75012006;22;19;6;3;7
data class Player(
    val name: String,
    var team: Team,
    var position: String,
    var nationality: String,
    val agency: String,
    var transfercost: Int,
    var participations: Int,
    var goals: Int,
    var assists: Int,
    var yellow_cards: Int,
    var red_cards: Int,
)
