import { useEffect, useState } from "react";
import GetAxios from "../utils/axios/GetAxios";
import {useNavigate} from "react-router-dom";
import MyButton from "./button/MyButton";
import PostAxios from "../utils/axios/PostAxios";
import DeleteAxios from "../utils/axios/DeleteAxios";
import PutAxios from "../utils/axios/PutAxios";


const MyContestComments = ({paintingId}) => {
    
    const [ comments, setComments ] = useState([]);
    const [ newComment, setNewComment] = useState("");
    const [ editComment, setEditComment ] = useState("");
    const [ activeEdit , setActiveEdit ] = useState();
    const navigate = useNavigate();

    const userIdOnSession = sessionStorage.getItem("userId");

    useEffect(()=>{
        GetAxios('/arts-center/painting/'+paintingId, {params:{}},(res)=>{
            setComments(res.data)
        })
    },[])

    const Changehandler = (e) =>{
        setNewComment(e.target.value);
    }


    const AddComment = () => {
        PostAxios('/arts-center/painting/comment',{contents:newComment, contestPaintingId:paintingId,memberId:sessionStorage.getItem("userId")},
            (res)=>{window.location.reload()})
    }

    const DeleteComment = (commentId) => {
        DeleteAxios('/arts-center/painting/comment/'+ commentId, {params:{}}, (res)=>{window.location.reload()})
    }


    return(
        <div className="commnets_wrapper">
            <span className="text" onClick={()=> navigate(-1)}> 이전으로 </span>
            {comments.length > 0 ? comments.map(comment=> (
                <div key={comment.commentId}>
                    <div className="user_info">
                        <p className="user_info_detail">
                            <span>{comment.nickname} ({comment.memberId}) </span>
                            <span className="time"> {comment.createDatetime}</span>
                            {userIdOnSession === comment.memberId ? 
                                        <button onClick={()=>DeleteComment(comment.commentId)}>삭제</button> : <></>}
                            </p>
                    </div>
                    <div>
                    <img className="comment_profile" src={comment.profileUrl}/>
                    </div>
                    <div className="post_box">
                        <span className="post_comment"> {comment.contents}</span>
                        <br/>
                        <br/>
                        <br/>
                    </div>
                </div>
            )) : <div className="no_comment"> 아직 등록된 댓글이 없습니다. 댓글을 등록해보세요 </div> 
            }
            <br/>
            <div className="input_box">
            <textarea className="input_comment" placeholder="인터넷은 우리가 함께 만들어가는 소중한 공간입니다. &#13;&#10;댓글 작성 시 타인에 대한 배려와 책임을 담아주세요.
                        매너있는 몽땅인이 됩시다." 
            maxLength='600' type='text' value={newComment} onChange={Changehandler}/>
            <MyButton text={'등록'} onClick={AddComment}/>
            </div>
        </div>
    );
}

export default MyContestComments;