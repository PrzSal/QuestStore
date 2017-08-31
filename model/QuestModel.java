package model;

public class QuestModel<T> extends AbstractTask<T> {

    public QuestModel(String name, Integer price, T questCategory ) {

        this.title = name;
        this.price = price;
        this.category = questCategory;

    }

    @Override
    public String toString() {

        String stringQuest = "Name quest: " + this.title + "Price: " + this.price + this.category.toString();

        return stringQuest;
    }
}
