import { useEffect, useState } from "react";
import PostAxios from "../../utils/axios/PostAxios";
import DeleteAxios from "../../utils/axios/DeleteAxios";

const PaintingHeart = ({paintingId, like, count}) => {
    const [data, setData] = useState([]);
    
    useEffect(()=>{
        setData({
            like: like,
            count: count,
        })
    }, []);

    const handleClickUnLike = ()=>{
        PostAxios('/painting/mongddang/' + paintingId, {}, (res)=>{
            setData({
                like: !data.like,
                count: data.count+1
            });
        })
    };

    const handleClickLike = ()=>{
        DeleteAxios('/painting/mongddang/', {params:{paintingId : paintingId}}, (res)=>{
            setData({
                like: !data.like,
                count: data.count-1
            });
        })
    };

    return (
    <div>
        { 
            data.like ? 
            <img className="icon" src="../.././img/like.png" alt="like" onClick={handleClickLike}/> : 
            <img className="icon" src="../.././img/unlike.png" alt="unlike" onClick={handleClickUnLike}/>
        }
        {data.count}
    </div>
    );
}

export default PaintingHeart;