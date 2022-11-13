
const MyFeed = () => {
    return (
        <div className="my_feed_wrapper">
            <div className="items">
                <img className="items_img" src='../.././img/test13.jpeg' alt="drawing"/>
            </div>
            <div className="items_desc">
                <img className="items_profile" src='../.././img/test10.jpeg' alt="profile"/>
                <span className="user_info">지유(jeeyu)</span>
        <br/>
                 <span className="user_info">2022-11-11 21:32</span>
        <br/>
                <p className="items_title">어떤 이미지를 주로 많이 참고하세요?</p>
                <p className="items_content">안녕하세요 예전부터 그림그리는 걸 너무 좋아하던 사람입니다. 그림을 그릴 때 저는 핀터레스트나 
                외국 사이트에서 보고 따라하는데 다른 분들 보시면 평소 좋아하던 물건의... </p>
            <span className="user_like"> <img className="icon" src="../.././img/like.png" alt="like"/> 28 </span>
            <span className="user_like"><img className="icon" src="../.././img/comment.png" alt="comment"/> 81 </span>
            </div>

            <div className="items">
                <img className="items_img" src='../.././img/test.jpg' alt="drawing"/>
            </div>
            <div className="items_desc">
                <img className="items_profile" src='../.././img/test7.jpeg' alt="profile"/>
                <span className="user_info">재석(jaeseok)</span>
        <br/>
            <span className="user_info">2022-11-02 10:32</span>
        <br/>
                <p className="items_title">어제 갔던 진하 해수욕장 그림ㅎㅎ</p>
                <p className="items_content">잘 그렸죠? 그림그리는거 요즘 재밌음ㅋㅋ </p>
                <span className="user_like"> <img className="icon" src="../.././img/like.png" alt="like"/> 5 </span>
                <span className="user_like"><img className="icon" src="../.././img/comment.png" alt="comment"/> 11 </span>
            </div>
        </div>
    );
}

export default MyFeed;