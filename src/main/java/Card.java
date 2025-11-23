public record Card(Rank rank, Suit suit) {

    public boolean isJoker() {
        return rank.getValue() == -1;
    }
}
