INSERT INTO categories (parent_id, name)
VALUES  (NULL, 'Овощи, фрукты'),
        (NULL, 'Кулинария'),
        (NULL, 'Мясо, Рыба, Гастрономия'),
        (NULL, 'Молоко, Сыр, Яйцо'),
        (NULL, 'Замороженные продукты'),
        (NULL, 'Бакалея'),
        (NULL, 'Булочная, кондитерская'),
        (NULL, 'Напитки'),
        (NULL, 'Алкоголь'),
        (NULL, 'Чистота, порядок'),
        (NULL, 'Товары для животных'),
        (NULL, 'Красота, здоровье');

WITH cat_id_statement AS (
    SELECT id
    FROM categories
    WHERE name = 'Овощи, фрукты'
)
INSERT INTO categories (parent_id, name)
VALUES  ((SELECT id FROM cat_id_statement), 'Овощи'),
        ((SELECT id FROM cat_id_statement), 'Фрукты'),
        ((SELECT id FROM cat_id_statement), 'Ягоды'),
        ((SELECT id FROM cat_id_statement), 'Молоко. Сыр, Яйцо'),
        ((SELECT id FROM cat_id_statement), 'Зелень, салаты'),
        ((SELECT id FROM cat_id_statement), 'Грибы'),
        ((SELECT id FROM cat_id_statement), 'Фрукты овощи резаные'),
        ((SELECT id FROM cat_id_statement), 'Фруктовое пюре, желе, десерты');

WITH cat_id_statement AS (
    SELECT id
    FROM categories
    WHERE name = 'Кулинария'
)
INSERT INTO categories (parent_id, name)
VALUES  ((SELECT id FROM cat_id_statement), 'Блюда на заказ'),
        ((SELECT id FROM cat_id_statement), 'Завтраки'),
        ((SELECT id FROM cat_id_statement), 'Сэндвичи'),
        ((SELECT id FROM cat_id_statement), 'Салаты, закуски'),
        ((SELECT id FROM cat_id_statement), 'Горячие блюда'),
        ((SELECT id FROM cat_id_statement), 'Супы'),
        ((SELECT id FROM cat_id_statement), 'Кухни мира'),
        ((SELECT id FROM cat_id_statement), 'Веганские блюда'),
        ((SELECT id FROM cat_id_statement), 'Считаем калории'),
        ((SELECT id FROM cat_id_statement), 'Соленья'),
        ((SELECT id FROM cat_id_statement), 'Равиолли , паста'),
        ((SELECT id FROM cat_id_statement), 'Готовые к запеканию блюда');

WITH cat_id_statement AS (
    SELECT id
    FROM categories
    WHERE name = 'Мясо, Рыба, Гастрономия'
)
INSERT INTO categories (parent_id, name)
VALUES  ((SELECT id FROM cat_id_statement), 'Шашлыки, барбекю'),
        ((SELECT id FROM cat_id_statement), 'Стейки'),
        ((SELECT id FROM cat_id_statement), 'Мясо'),
        ((SELECT id FROM cat_id_statement), 'Птица'),
        ((SELECT id FROM cat_id_statement), 'Полуфабрикаты из мяса, птицы'),
        ((SELECT id FROM cat_id_statement), 'Мясная гастрономия'),
        ((SELECT id FROM cat_id_statement), 'Морепродукты охлаждённые'),
        ((SELECT id FROM cat_id_statement), 'Рыба охлаждённая'),
        ((SELECT id FROM cat_id_statement), 'Полуфабрикаты из рыбы и морепродуктов'),
        ((SELECT id FROM cat_id_statement), 'Икра'),
        ((SELECT id FROM cat_id_statement), 'Рыбная гастрономия');

WITH cat_id_statement AS (
    SELECT id
    FROM categories
    WHERE name = 'Молоко, Сыр, Яйцо'
)
INSERT INTO categories (parent_id, name)
VALUES  ((SELECT id FROM cat_id_statement), 'Мороженое'),
        ((SELECT id FROM cat_id_statement), 'Молоко, Сливки'),
        ((SELECT id FROM cat_id_statement), 'Продукты с заменителями молока'),
        ((SELECT id FROM cat_id_statement), 'Детское питание'),
        ((SELECT id FROM cat_id_statement), 'Творог, Сырки, Десерты'),
        ((SELECT id FROM cat_id_statement), 'Сметана'),
        ((SELECT id FROM cat_id_statement), 'Кисломолочные продукты'),
        ((SELECT id FROM cat_id_statement), 'Йогурты'),
        ((SELECT id FROM cat_id_statement), 'Сыры'),
        ((SELECT id FROM cat_id_statement), 'Масло сливочное'),
        ((SELECT id FROM cat_id_statement), 'Майонез'),
        ((SELECT id FROM cat_id_statement), 'Яйцо'),
        ((SELECT id FROM cat_id_statement), 'Сгущенное молоко , коктейли');

WITH cat_id_statement AS (
    SELECT id
    FROM categories
    WHERE name = 'Замороженные продукты'
)
INSERT INTO categories (parent_id, name)
VALUES  ((SELECT id FROM cat_id_statement), 'Мороженое, десерты'),
        ((SELECT id FROM cat_id_statement), 'Рыба'),
        ((SELECT id FROM cat_id_statement), 'Морепродукты'),
        ((SELECT id FROM cat_id_statement), 'Овощи, смеси, грибы'),
        ((SELECT id FROM cat_id_statement), 'Фрукты, ягоды'),
        ((SELECT id FROM cat_id_statement), 'Пельмени, вареники'),
        ((SELECT id FROM cat_id_statement), 'Пицца, хлеб, блины, тесто'),
        ((SELECT id FROM cat_id_statement), 'Мясо, птица');

WITH cat_id_statement AS (
    SELECT id
    FROM categories
    WHERE name = 'Бакалея'
)
INSERT INTO categories (parent_id, name)
VALUES  ((SELECT id FROM cat_id_statement), 'Мука, соль, сахар'),
        ((SELECT id FROM cat_id_statement), 'Ингредиенты для выпечки'),
        ((SELECT id FROM cat_id_statement), 'Макароны, крупы'),
        ((SELECT id FROM cat_id_statement), 'Готовые завтраки, каши, мюсли'),
        ((SELECT id FROM cat_id_statement), 'Продукты быстрого приготовления'),
        ((SELECT id FROM cat_id_statement), 'Соусы. Масла. Уксусы'),
        ((SELECT id FROM cat_id_statement), 'Чай, кофе, какао'),
        ((SELECT id FROM cat_id_statement), 'Консервированные продукты'),
        ((SELECT id FROM cat_id_statement), 'Варенье. Джем. Мед'),
        ((SELECT id FROM cat_id_statement), 'Орехи. Сухофрукты'),
        ((SELECT id FROM cat_id_statement), 'Десертные пасты и сиропы'),
        ((SELECT id FROM cat_id_statement), 'Снеки. Жевательная резинка'),
        ((SELECT id FROM cat_id_statement), 'Специи, приправы'),
        ((SELECT id FROM cat_id_statement), 'Полезные добавки'),
        ((SELECT id FROM cat_id_statement), 'Азиатская кухня');

WITH cat_id_statement AS (
    SELECT id
    FROM categories
    WHERE name = 'Булочная, кондитерская'
)
INSERT INTO categories (parent_id, name)
VALUES  ((SELECT id FROM cat_id_statement), 'Торты'),
        ((SELECT id FROM cat_id_statement), 'Пирожные, десерты'),
        ((SELECT id FROM cat_id_statement), 'Хлеб, булочки, лепешки'),
        ((SELECT id FROM cat_id_statement), 'Выпечка'),
        ((SELECT id FROM cat_id_statement), 'Хлебные изделия'),
        ((SELECT id FROM cat_id_statement), 'Конфеты'),
        ((SELECT id FROM cat_id_statement), 'Вафли, пряники, печенье'),
        ((SELECT id FROM cat_id_statement), 'Шоколад, Шоколадные батончики'),
        ((SELECT id FROM cat_id_statement), 'Сладости'),
        ((SELECT id FROM cat_id_statement), 'Десертные пасты и сиропы'),
        ((SELECT id FROM cat_id_statement), 'Замороженные торты, выпечка, тесто');

WITH cat_id_statement AS (
    SELECT id
    FROM categories
    WHERE name = 'Напитки'
)
INSERT INTO categories (parent_id, name)
VALUES  ((SELECT id FROM cat_id_statement), 'Вода'),
        ((SELECT id FROM cat_id_statement), 'Морсы, кисели, компоты'),
        ((SELECT id FROM cat_id_statement), 'Соки, Нектары'),
        ((SELECT id FROM cat_id_statement), 'Газированные напитки, лимонады'),
        ((SELECT id FROM cat_id_statement), 'Квас, холодный чай, кофе'),
        ((SELECT id FROM cat_id_statement), 'Энергетики, функциональные напитки'),
        ((SELECT id FROM cat_id_statement), 'Детская вода и напитки'),
        ((SELECT id FROM cat_id_statement), 'Смузи, Комбуча'),
        ((SELECT id FROM cat_id_statement), 'Сиропы'),
        ((SELECT id FROM cat_id_statement), 'Безалкогольное пиво, вино');

WITH cat_id_statement AS (
    SELECT id
    FROM categories
    WHERE name = 'Алкоголь'
)
INSERT INTO categories (parent_id, name)
VALUES  ((SELECT id FROM cat_id_statement), 'Вино'),
        ((SELECT id FROM cat_id_statement), 'Крепкие напитки'),
        ((SELECT id FROM cat_id_statement), 'Шампанское и игристые вина'),
        ((SELECT id FROM cat_id_statement), 'Корзины и наборы АВ с алкоголем'),
        ((SELECT id FROM cat_id_statement), 'Пиво');

WITH cat_id_statement AS (
    SELECT id
    FROM categories
    WHERE name = 'Чистота, порядок'
)
INSERT INTO categories (parent_id, name)
VALUES  ((SELECT id FROM cat_id_statement), 'Средства для уборки'),
        ((SELECT id FROM cat_id_statement), 'Средства для стирки и ухода за вещами'),
        ((SELECT id FROM cat_id_statement), 'Предметы для уборки'),
        ((SELECT id FROM cat_id_statement), 'Уход за одеждой и обувью');

WITH cat_id_statement AS (
    SELECT id
    FROM categories
    WHERE name = 'Товары для животных'
)
INSERT INTO categories (parent_id, name)
VALUES  ((SELECT id FROM cat_id_statement), 'Корма для кошек'),
        ((SELECT id FROM cat_id_statement), 'Корма для собак'),
        ((SELECT id FROM cat_id_statement), 'Наполнители, пеленки'),
        ((SELECT id FROM cat_id_statement), 'Аксессуары для животных');

WITH cat_id_statement AS (
    SELECT id
    FROM categories
    WHERE name = 'Красота, здоровье'
)
INSERT INTO categories (parent_id, name)
VALUES  ((SELECT id FROM cat_id_statement), 'Уход за полостью рта'),
        ((SELECT id FROM cat_id_statement), 'Предметы личной гигиены'),
        ((SELECT id FROM cat_id_statement), 'Мужская косметика и гигиена'),
        ((SELECT id FROM cat_id_statement), 'Подарочные наборы'),
        ((SELECT id FROM cat_id_statement), 'Женская гигиена'),
        ((SELECT id FROM cat_id_statement), 'Уход за волосами'),
        ((SELECT id FROM cat_id_statement), 'Уход за руками и ногтями'),
        ((SELECT id FROM cat_id_statement), 'Уход за лицом'),
        ((SELECT id FROM cat_id_statement), 'Уход за ногами'),
        ((SELECT id FROM cat_id_statement), 'Уход за телом'),
        ((SELECT id FROM cat_id_statement), 'Спортивное питание'),
        ((SELECT id FROM cat_id_statement), 'Товары для здоровья'),
        ((SELECT id FROM cat_id_statement), 'Баня-сауна, СПА'),
        ((SELECT id FROM cat_id_statement), 'Защитные сезонные средства'),
        ((SELECT id FROM cat_id_statement), 'Носки, колготки, чулки');