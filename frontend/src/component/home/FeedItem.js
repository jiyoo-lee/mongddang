import { useNavigate } from "react-router-dom";
import Heart from "../like/Heart";

const FeedItem = ({painting}) => {
    const navigate = useNavigate();

    return (
        <>
            <div className="items">
                <img className="items_img" src={painting.paintingUrl} alt="drawing"/>
                </div>

                <div className="items_desc">
                <img className="items_profile" src={painting.profileUrl} onClick={()=>navigate('/drawer/'+painting.memberId)} alt="profile"/>
                <span className="user_info">{painting.nickname}({painting.memberId})</span>
                <br/>
                <span className="user_info">{painting.createDatetime}</span>
                <br/>
                <p className="items_title">{painting.name}</p>
                <p className="items_content">{painting.description}</p>
                <br/>
                <Heart id={painting.paintingId} like={painting.isLike} count={painting.mongddangCount} type="painting"/>
                <span className="user_like"><img className="icon" src="../.././img/comment.png" alt="comment" 
                        onClick={()=>navigate('/comments/'+painting.paintingId)}/>
                    {painting.comment}</span>
            </div>
        </>
    );
}

export default FeedItem;