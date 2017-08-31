package model;

public class QuestModel<T> extends AbstractTask<T> {

    public QuestModel(String name, Integer price, T questCategory ) {

        this.name = name;
        this.price = price;
        this.questCategory = questCategory;

    }

    @Override
    public String toString() {

        String stringQuest = "Name quest: " + this.name + "Price: " this.price + this.questCategory.toString();

        return stringQuest;
}
