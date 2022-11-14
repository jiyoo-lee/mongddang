import { useState } from "react";
import  getAxiosFn  from "../../../utils/axios/getAxiosFn";


const MyBest = () => {

    const [option, setOption] = useState([]);

    const genre = () =>{
    getAxiosFn('/genre', {param:{}})
    .then(res=>{ setOption(res)})};

    genre();

    return (
        <>
        <div className="select_wrapper">
            장르 별 <select className="select_box">
            {option.map(op=>(
                    <option key={op.id} value={op.id}>{op.name}</option>
                ))}
            </select>
        </div>
        <div className="my_feed_wrapper">
            <div className="items">
                <img className="items_img" src='../.././img/test7.jpeg' alt="drawing"/>
            </div>
            <div className="items_desc">
                <img className="items_profile" src='../.././img/test12.jpeg' alt="profile"/>
                <span className="user_info">짱돌(spring12)</span>
        <br/>
                 <span className="user_info">2022-11-11 21:32</span>
        <br/>
                <p className="items_title">실시간 그림~~</p>
                <p className="items_content">재밌다 요즘!!!!! </p>
            <span className="user_like"> <img className="icon" src="../.././img/like.png" alt="like"/> 28 </span>
            <span className="user_like"><img className="icon" src="../.././img/comment.png" alt="comment"/> 81 </span>
            </div>

            <div className="items">
                <img className="items_img" src='../.././img/test14.jpeg' alt="drawing"/>
            </div>
            <div className="items_desc">
                <img className="items_profile" src='../.././img/test5.jpeg' alt="profile"/>
                <span className="user_info">도라에몽(doradora12)</span>
        <br/>
            <span className="user_info">2022-11-02 10:32</span>
        <br/>
                <p className="items_title">새로운 라이브러리 다운 받았음ㅎㅎ</p>
                <p className="items_content">ㅋㅋㅋㅋㅋ나름 만족함 </p>
                <span className="user_like"> <img className="icon" src="../.././img/like.png" alt="like"/> 5 </span>
                <span className="user_like"><img className="icon" src="../.././img/comment.png" alt="comment"/> 11 </span>
            </div>
        </div>
        </>
    );
}

export default MyBest;