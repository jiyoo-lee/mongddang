import { useEffect, useState } from "react";
import  GetAxios  from "../../../utils/axios/GetAxios";


const MyBest = () => {

    const [option, setOption] = useState([]);
    const [ paintingsByGenre , setPaintingByGenre ] = useState([]); 

    useEffect(()=>{
        GetAxios('/genre',{params:{}},(res)=>{setOption(res.data)})
    },[])

    const onUpdate = (genreId) => {
        console.log(genreId)
        GetAxios('painting/popular/genre/'+genreId,{params:{}},(res)=>{
            setPaintingByGenre(res.data);
        })
    }
    return (
        <>
        <div className="select_wrapper">
            장르 별 <select className="select_box" onChange={(e)=>onUpdate(e.target.value)}>
            {option.map(op=>(
                    <option key={op.id} value={op.id}>{op.name}</option>
                ))}
            </select>
        </div>
        <div className="my_feed_wrapper">
            {paintingsByGenre.map(painting => (
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

export default MyBest;