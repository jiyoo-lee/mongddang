import Heart from "../like/Heart";
import { useNavigate } from "react-router-dom";

const PaintingItem = (props) => {

    const navigate = useNavigate();

    console.log(props)
    return (
        <div key={props.painting.paintingId}>
        <div className="my_feed_wrapper">
            <div className="items">
            <img className="items_img" src={props.painting.paintingUrl} alt="drawing"/>
            </div>
            <div className="items_desc">
            <span className="genre">{props.painting.genre}</span>
            <span className="user_info">  {props.painting.createDatetime}</span>
            <p className="items_title">{props.painting.name}</p>
            <p className="items_content">{props.painting.description}</p>
            <Heart id={props.painting.paintingId} like={props.painting.isLike} count={props.painting.mongddangCount} type="painting"/>
            <span className="user_like"><img className="icon" src="../.././img/comment.png" alt="comment" 
                    onClick={()=>navigate('/comments/'+props.painting.paintingId)}/>
                {props.painting.comment}</span>
        <br/>
            </div>
        </div>
        </div>
    );
}

export default PaintingItem;