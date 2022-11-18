import { useEffect, useState } from "react";
import GetAxios from "../../../utils/axios/GetAxios";
import PaintingHeart from "../../like/PaintingHeart";
import Comments from "../../Comments";
import { useNavigate } from "react-router-dom";

const MyNow = () => {

    const [allPaintings, setAllPaintings] = useState([]);
    const navigate = useNavigate();
    

    useEffect(()=>{
        GetAxios('/painting/',{params:{}},(res)=>{setAllPaintings(res.data)})
    },[])


    return(
        <>
        <div className="my_feed_wrapper">
            {allPaintings.map(painting => (
            <div key={painting.paintingId}>
            <div className="items">
                <img className="items_img" src={painting.paintingUrl} alt="drawing"/>
            </div>
            <div className="items_desc">
                <img className="items_profile" src={painting.profileUrl} alt="profile"/>
                <span className="user_info">{painting.nickname}({painting.memberId})</span>
        <br/>
                 <span className="user_info">{painting.createDatetime}</span>
        <br/>
                <p className="items_title">{painting.name}</p>
                <p className="items_content">{painting.description}</p>
                <PaintingHeart paintingId={painting.paintingId} like={painting.isLike} count={painting.mongddangCount}/>
            <span className="user_like">
                <img className="icon" src="../.././img/comment.png" alt="comment" 
                    onClick={()=>navigate('/comments/'+painting.paintingId)}/>
                {painting.comment}</span>
             </div> 
           </div>
            ))} 
        </div>
        </>
    );
}

export default MyNow;