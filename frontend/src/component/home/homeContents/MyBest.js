import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import  GetAxios  from "../../../utils/axios/GetAxios";
import PaintingHeart from "../../like/PaintingHeart";



const MyBest = () => {

    const [option, setOption] = useState([]);
    const [ paintingsByGenre , setPaintingByGenre ] = useState([]); 
  
    const navigate = useNavigate();

    useEffect(()=>{
        GetAxios('/genre',{params:{}},(res)=>{setOption(res.data)})
        GetAxios('/painting/popular/genre/1',{params:{}},(res)=>{setPaintingByGenre(res.data)})
    },[])

    const onUpdate = (genreId) => {
        

        GetAxios('/painting/popular/genre/'+genreId,{params:{}},(res)=>{
            console.log(res.data)
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
                 <span className="user_info">{painting.createDatetime}</span>
        <br/>
                <p className="items_title">{painting.name}</p>
                <p className="items_content">{painting.description}</p>
            <div className="user_reaction">
                <PaintingHeart paintingId={painting.paintingId} like={painting.isLike} count={painting.mongddangCount}/>
                <span className="user_like"><img className="icon" src="../.././img/comment.png" alt="comment" 
                    onClick={()=>navigate('/comments/'+painting.paintingId)}/>
                {painting.comment}</span>
                
            </div>
            </div>
            </div>
            )) }
        </div>
        </>
    );
}

export default MyBest;