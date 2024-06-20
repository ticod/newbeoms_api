const newsMainList = document.getElementsByClassName("news-main");
const newsLeftArrowButton = document.getElementById("news_left_arrow_button");
const newsRightArrowButton = document.getElementById("news_right_arrow_button");
const newsButton = document.getElementById("news_button");

const PUBLICATION_URI = "/publication/";

let newsCount = newsMainList.length - 1;

function showNews(targetNewsCount) {
    for (let i = 0; i < newsMainList.length; i++) {
        if (i !== targetNewsCount) {
            newsMainList[i].style.display = "none";
        } else {
            newsMainList[i].style.display = "block";
        }
    }
}

showNews(newsCount);

newsLeftArrowButton.addEventListener('click', () => {
    newsCount--;
    if (newsCount < 0) {
        newsCount = newsMainList.length - 1;
    }
    showNews(newsCount);
});

newsRightArrowButton.addEventListener('click', () => {
    newsCount = (++newsCount) % newsMainList.length;
    showNews(newsCount);
});

newsButton.addEventListener('click', () => {
    window.location.href = PUBLICATION_URI + newsMainList[newsCount].id;
});