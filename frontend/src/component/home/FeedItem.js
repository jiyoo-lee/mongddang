
const FeedItem = (props) => {
    return (
        <>
        <div className="items">
        <img className="items_img" src={props.painting.paintingUrl} alt="drawing"/>
    </div>
    <div className="items_desc">
        <img className="items_profile" src={props.painting.profileUrl} alt="profile"/>
        <span className="user_info">{props.painting.nickname}({props.painting.memberId})</span>
<br/>
         <span className="user_info">2022-11-11 21:32</span>
<br/>
        <p className="items_title">{props.painting.name}</p>
        <p className="items_content">{props.painting.description}</p>
    <span className="user_like"> <img className="icon" src="../.././img/like.png" alt="like"/> {props.painting.mongddangCount} </span>
    <span className="user_like"><img className="icon" src="../.././img/comment.png" alt="comment"/> 81 </span>
    </div>
    </>
    );
}

export default FeedItem;