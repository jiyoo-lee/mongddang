import { useEffect, useState } from "react";
import PostAxios from "../../utils/axios/PostAxios";
import DeleteAxios from "../../utils/axios/DeleteAxios";

const Heart = ({id, like, count, type}) => {
    const [data, setData] = useState([]);
    
    useEffect(()=>{
        setData({
            like: like,
            count: count,
        })
    }, []);


    const handleClickUnLike = ()=>{
        if (type==='painting') {
            PostAxios('/painting/mongddang/' + id, {}, res=>setData({
                like: !data.like,
                count: data.count+1
            }));
        }
        else if (type==='drops') {
            PostAxios('/drops/mongddang/' + id, {}, res=>setData({
                like: !data.like,
                count: data.count+1
            }));
        }
        else if (type==='contest') {
            PostAxios('/painting/' + id + '/mongddang', {}, res=>{
                setData({
                like: !data.like,
                count: data.count+1
            }
            )
            }
            );
        }
    };

    const handleClickLike = ()=>{
        if (type==='painting') {
            DeleteAxios('/painting/mongddang/', {params:{paintingId : id}}, res=>setData({
                like: !data.like,
                count: data.count-1
            }));
        }
        else if (type==='drops') {
            DeleteAxios('/drops/mongddang/' + id, {params:{}}, res=>setData({
                like: !data.like,
                count: data.count-1
            }));
        }
        else if (type==='contest') {
            DeleteAxios('/painting/' + id + '/mongddang', {params:{}}, res=>setData({
                like: !data.like,
                count: data.count-1
            }));
        }
    };

    return (
    <>
        { 
            data.like ? 
            <img className="icon" src="../.././img/like.png" alt="like" onClick={handleClickLike}/> : 
            <img className="icon" src="../.././img/unlike.png" alt="unlike" onClick={handleClickUnLike}/>
        }
        {data.count}
    </>
    );
}

export default Heart;