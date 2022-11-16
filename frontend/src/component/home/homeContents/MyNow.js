import { useEffect, useState } from "react";
import GetAxios from "../../../utils/axios/GetAxios";

const MyNow = () => {

    const [option, setOption] = useState([]);
    const [allPaintings, setAllPaintings] = useState([]);

    useEffect(()=>{
        GetAxios('/painting',{params:{}},(res)=>{setAllPaintings(res.data)})
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
                 <span className="user_info">2022-11-11 21:32</span>
        <br/>
                <p className="items_title">{painting.name}</p>
                <p className="items_content">{painting.decription}</p>
            <span className="user_like"> <img className="icon" src="../.././img/like.png" alt="like"/>{painting.mongddangCount}</span>
            <span className="user_like"><img className="icon" src="../.././img/comment.png" alt="comment"/>댓글수</span> 
             </div> 
           </div>
            ))} 
        </div>
        </>
    );
}

export default MyNow;