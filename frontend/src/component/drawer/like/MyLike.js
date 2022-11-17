import { useEffect, useState } from "react";
import GetAxios from "../../../utils/axios/GetAxios";
import { useNavigate } from "react-router-dom";

const MyLike = () => {

    const [likes, setLikes] = useState([]);
    const navigate = useNavigate();

    useEffect(()=>{
        GetAxios('/painting/like/'+sessionStorage.getItem("userId"),{params:{}},(res)=>{setLikes(res.data)})
    },[])

    const goToPage = (memberId) => {
        navigate('/drawer/'+memberId)
    }


    return (
        <div className="drawer_wrapper">
            
        <span className="userid_message">내가 좋아요한 순간들</span>
        <br/>
        <br/>
        <br/>
        { likes && likes.length > 0 ? likes.map(like => (
            <div key={like.paintingUrl} className="drops_list">
                <img className="items_img" src={like.paintingUrl} onClick={()=>goToPage(like.memberId)}/> <br/>
                <span className="drawer_msg">{like.name}</span> <br/><br/>
            </div>
        )): <></>}
        </div>
    );
}

export default MyLike;