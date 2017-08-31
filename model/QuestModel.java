package model;

public class QuestModel extends AbstractTask<QuestCategoryModel> {

    public QuestModel(String name, Integer price, QuestCategoryModel questCategory ) {

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
